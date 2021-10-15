package com.inducesmile.dogapplication.viewmodel;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.inducesmile.dogapplication.database.DogDAO;
import com.inducesmile.dogapplication.database.DogDatabase;
import com.inducesmile.dogapplication.database.entities.DogEntity;
import com.inducesmile.dogapplication.models.Dog;
import com.inducesmile.dogapplication.network.IDogAPI;
import com.inducesmile.dogapplication.network.RetroInstance;
import com.inducesmile.dogapplication.utils.DeleteCache;
import com.inducesmile.dogapplication.utils.GetDataFromDB;
import com.inducesmile.dogapplication.utils.InsertDataToDB;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {
    private static final String TAG = "MainActivityViewModel";

    private final DogDAO dao;
    private List<DogEntity> models;
    private MutableLiveData<List<DogEntity>> listData;
    private boolean isInternetConnected;
    private final SharedPreferences preferences;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        DogDatabase database = DogDatabase.getInstance(application);
        dao = database.dogDAO();
        preferences = PreferenceManager.getDefaultSharedPreferences(application);
        models = new ArrayList<>();
        isInternetConnected = isNetworkAvailable(application);
    }

    public LiveData<List<DogEntity>> getData(){
        boolean isCacheExpire = false;
        long cacheTime = preferences.getLong("cache", 0);
        if(listData==null){
            listData = new MutableLiveData<>();
            if(isInternetConnected){
                if(cacheTime>0){
                    long currentTime = new Date().getTime();
                    long difference = currentTime - cacheTime;
                    long seconds = difference / 1000;
                    if (seconds > 20) {
                        isCacheExpire = true;
                    }
                }
                if (models.size() > 0 && !isCacheExpire) {
                    models = readCache();
                    listData.postValue(models);
                } else {
                    loadData();
                }
            } else {
                models = readCache();
                listData.postValue(models);
            }
        }
        return listData;
    }

    private void loadData() {
        IDogAPI dogAPI = RetroInstance.getRetroInstance().create(IDogAPI.class);
        Call<List<Dog>> call = dogAPI.getAll();
        call.enqueue(new Callback<List<Dog>>() {
            @Override
            public void onResponse(@NonNull Call<List<Dog>> call, @NonNull Response<List<Dog>> response) {
                if(response.isSuccessful()){
                    List<Dog> list = response.body();
                    if (list != null) {
//                        new InsertDataToDB(models,list,dao,mHandler).start();
                        for (Dog mResponse: list) {
                            models.add(mResponse.casToEntity());
                        }
                        AsyncTask.execute(() -> dao.InsertDogs(models));
                    }
                    listData.postValue(models);
                    preferences.edit().putLong("cache", new Date().getTime()).apply();
                } else {
                    listData.postValue(models);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Dog>> call, @NonNull Throwable t) {
                listData.postValue(models);
            }
        });
    }

    private List<DogEntity> readCache(){
        List<DogEntity> cache = new ArrayList<>();
        AsyncTask.execute(() -> cache.addAll(dao.getAll()));
//        new GetDataFromDB(cache,dao,mHandler).start();
        return cache;
    }

    public List<DogEntity> searchContact(String query){
        List<DogEntity> result = new ArrayList<>();
        AsyncTask.execute(() -> result.addAll(dao.search(query)));
        return result;
    }

    private boolean isNetworkAvailable(Application app) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) app.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public List<DogEntity> onRestart() {
        listData.setValue(readCache());
        return listData.getValue();
    }

    public List<DogEntity> onSearch(String search){
        String searchQuery = "%"+search+"%";
        listData.setValue(searchContact(searchQuery));
        return listData.getValue();
    }
}
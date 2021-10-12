package com.inducesmile.dogapplication.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.inducesmile.dogapplication.models.Dog;
import com.inducesmile.dogapplication.network.IDogAPI;
import com.inducesmile.dogapplication.network.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<List<Dog>> listData;
    public LiveData<List<Dog>> getData(){
        if(listData==null){
            listData = new MutableLiveData<>();
            loadData();
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
                    listData.postValue(response.body());
                } else {
                    listData.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Dog>> call, @NonNull Throwable t) {
                listData.postValue(null);
            }
        });
    }
}

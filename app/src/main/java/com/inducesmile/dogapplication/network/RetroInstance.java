package com.inducesmile.dogapplication.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroInstance {
    private static Retrofit retrofit;
    private static IDogAPI AppApi;
    public static final String BASE_URL = "https://raw.githubusercontent.com/";

    public static Retrofit getRetroInstance() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        if(retrofit == null) {
            retrofit =  new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static IDogAPI getAppApiInterface(){
        if(AppApi == null){
            AppApi = getRetroInstance().create(IDogAPI.class);
        }
        return AppApi;
    }
}

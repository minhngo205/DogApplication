package com.inducesmile.dogapplication.network;

import com.inducesmile.dogapplication.models.Dog;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IDogAPI {
    @GET("DevTides/DogsApi/master/dogs.json")
    Call<List<Dog>> getAll();
}

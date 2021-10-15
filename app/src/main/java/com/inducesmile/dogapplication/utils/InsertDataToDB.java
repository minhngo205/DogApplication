package com.inducesmile.dogapplication.utils;

import android.os.Handler;

import com.inducesmile.dogapplication.database.DogDAO;
import com.inducesmile.dogapplication.database.entities.DogEntity;
import com.inducesmile.dogapplication.models.Dog;

import java.util.List;

public class InsertDataToDB extends Thread {
    List<DogEntity> dogEntityList;
    List<Dog> dogList;
    DogDAO dogDAO;
    Handler mHandler;

    public InsertDataToDB(List<DogEntity> entityList, List<Dog> list, DogDAO dao, Handler handler){
        dogEntityList = entityList;
        dogList = list;
        dogDAO = dao;
        mHandler = handler;
    }

    @Override
    public void run() {
        for (Dog d:dogList) {
            dogEntityList.add(d.casToEntity());
        }
        mHandler.post(() -> dogDAO.InsertDogs(dogEntityList));
    }
}
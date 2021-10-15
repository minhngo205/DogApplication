package com.inducesmile.dogapplication.utils;

import android.os.Handler;

import com.inducesmile.dogapplication.database.DogDAO;
import com.inducesmile.dogapplication.database.entities.DogEntity;

import java.util.List;

public class GetDataFromDB extends Thread {
    List<DogEntity> dogEntityList;
    DogDAO dogDAO;
    Handler mHandler;

    public GetDataFromDB(List<DogEntity> list, DogDAO dao, Handler handler){
        dogEntityList = list;
        dogDAO = dao;
        mHandler = handler;
    }

    @Override
    public void run() {
        mHandler.post(() -> dogEntityList = dogDAO.getAll());
    }
}
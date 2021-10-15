package com.inducesmile.dogapplication.utils;

import android.os.Handler;

import com.inducesmile.dogapplication.database.DogDAO;
import com.inducesmile.dogapplication.database.entities.DogEntity;

import java.util.List;

public class DeleteCache extends Thread{
    DogDAO dogDAO;
    Handler mHandler;

    public DeleteCache(DogDAO dao, Handler handler){
        dogDAO = dao;
        mHandler = handler;
    }

    @Override
    public void run() {
        mHandler.post(() -> dogDAO.deleteAll());
    }
}
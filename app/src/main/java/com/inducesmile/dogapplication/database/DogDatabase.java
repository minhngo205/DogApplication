package com.inducesmile.dogapplication.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.inducesmile.dogapplication.database.entities.DogEntity;

@Database(entities = DogEntity.class, version = 2)
public abstract class DogDatabase extends RoomDatabase {
    public abstract DogDAO dogDAO();

    private static DogDatabase instance;

    public static DogDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context,
                    DogDatabase.class,"dogDB").build();
        }
        return instance;
    }
}

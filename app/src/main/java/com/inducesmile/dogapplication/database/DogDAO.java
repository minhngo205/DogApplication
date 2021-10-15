package com.inducesmile.dogapplication.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.inducesmile.dogapplication.database.entities.DogEntity;

import java.util.List;

@Dao
public interface DogDAO {
    String queryALL = "SELECT * FROM dog";
    String search = "SELECT * FROM dog WHERE name LIKE :searchStr";

    @Query(queryALL)
    List<DogEntity> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertDogs(List<DogEntity> dogs);

    @Query("DELETE FROM dog")
    void deleteAll();

    @Query(search)
    List<DogEntity> search(String searchStr);
}

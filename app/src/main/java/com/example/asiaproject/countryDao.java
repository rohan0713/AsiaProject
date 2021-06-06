package com.example.asiaproject;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface countryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<countries> crewList);

    @Query("DELETE FROM countries")
    void delete();

    @Query("SELECT * FROM countries")
    LiveData<List<countries>> showCountries();
}

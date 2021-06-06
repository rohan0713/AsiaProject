package com.example.asiaproject;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class countryViewmodel extends AndroidViewModel {

    private final countryRepository countryRepository;
    private final LiveData<List<countries>> getAllCountries;

    public countryViewmodel(@NonNull Application application) {
        super(application);
        countryRepository=new countryRepository(application);
        getAllCountries = countryRepository.getAllCountries();
    }

    public void insert(List<countries> list)
    {
        countryRepository.insert(list);
    }

    public void delete(){
        countryRepository.delete();
    }

    public LiveData<List<countries>> GetAllCountries()
    {
        return getAllCountries;
    }
}

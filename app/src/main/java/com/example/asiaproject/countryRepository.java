package com.example.asiaproject;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class countryRepository {

    private final countryDatabase database;
    private final LiveData<List<countries>> getCountries;

    public countryRepository(Application application)
    {
        database=countryDatabase.getInstance(application);
        getCountries=database.countryDao().showCountries();
    }

    public void insert(List<countries> countriesList){
        new InsertAsynTask(database).execute(countriesList);
    }

    public void delete(){
        new DeleteAsyncTask(database).execute();
    }

    public LiveData<List<countries>> getAllCountries()
    {
        return getCountries;
    }

    static class InsertAsynTask extends AsyncTask<List<countries>,Void,Void> {
        private final countryDao countryDao;
        InsertAsynTask(countryDatabase countryDatabase)
        {
            countryDao = countryDatabase.countryDao();
        }
        @Override
        protected Void doInBackground(List<countries>... lists) {
            countryDao.insert(lists[0]);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<Void, Void, Void>{

        private final countryDao countryDao;
        DeleteAsyncTask(countryDatabase countryDatabase){
            countryDao = countryDatabase.countryDao();
        }
        @Override
        protected Void doInBackground(Void[] voids) {
            countryDao.delete();
            return null;
        }
    }
}

package com.example.asiaproject;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {countries.class}, version = 1)
@TypeConverters({languageConverters.class, borderConverters.class})
public abstract class countryDatabase extends RoomDatabase {

    public static final String Database_Name = "countryDatabase";

    public abstract countryDao countryDao();

    private static volatile countryDatabase instance;

    public static countryDatabase getInstance(Context context){
        if(instance == null)
        {
            synchronized (countryDatabase.class){
                if(instance == null)
                {
                    instance= Room.databaseBuilder(context,countryDatabase.class,
                            Database_Name)
                            .addCallback(callback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }

    static Callback callback=new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsyncTask(instance);
        }
    };
    static class PopulateAsyncTask extends AsyncTask<Void,Void,Void>
    {
        private final countryDao countryDao;
        PopulateAsyncTask(countryDatabase countryDatabase)
        {
            countryDao=countryDatabase.countryDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            countryDao.delete();
            return null;
        }
    }
}

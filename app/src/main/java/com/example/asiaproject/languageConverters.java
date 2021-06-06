package com.example.asiaproject;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class languageConverters {

    @TypeConverter
    public String toString(List<LanguagesItem> languages) {
        if (languages == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<LanguagesItem>>() {
        }.getType();
        String json = gson.toJson(languages, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<LanguagesItem> fromString(String data) {
        if (data == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<LanguagesItem>>() {
        }.getType();
        List<LanguagesItem> list = gson.fromJson(data, type);
        return list;
    }
}

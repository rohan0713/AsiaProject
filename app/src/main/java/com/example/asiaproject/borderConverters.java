package com.example.asiaproject;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class borderConverters {

    @TypeConverter
    public String toString(List<String> borders) {
        if (borders == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        String json = gson.toJson(borders, type);
        return json;
    }

    @TypeConverter
    public List<String> fromString(String data) {
        if (data == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        List<String> list = gson.fromJson(data, type);
        return list;
    }
}

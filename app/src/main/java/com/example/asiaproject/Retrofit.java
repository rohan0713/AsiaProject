package com.example.asiaproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class Retrofit {

    private static final String base_url = "https://restcountries.eu/rest/v2/region/";

    public static Services Services = null;
    public static Services GetServices(){

        if(Services == null){

            retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Services = retrofit.create(Services.class);
        }
        return Services;
    }
    public interface Services{

        @GET("asia")
        Call<List<countries>> getCountriesInfo();
}
}

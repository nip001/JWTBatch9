package com.batch9.beritaappsjwt.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {
    public static Retrofit retrofit;
    public static String URL = "http://a9b0c7964550.ngrok.io";

    public static Retrofit getRetrofit(){
//        GsonBuilder gb = new GsonBuilder();
//        gb.registerTypeAdapter(String.class, new StringConverter());
//        gb.serializeNulls();
        // gb.excludeFieldsWithoutExposeAnnotation();
        Gson gson = new GsonBuilder().setLenient().create();

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}

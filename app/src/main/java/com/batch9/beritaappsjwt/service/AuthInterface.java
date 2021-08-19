package com.batch9.beritaappsjwt.service;

import com.batch9.beritaappsjwt.entity.Berita;
import com.batch9.beritaappsjwt.entity.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AuthInterface {
    @POST("/login")
    Call<String> loginUser(@Body User user);

    @POST("/register")
    Call<String> daftarUser(@Body User user);

    @GET("/berita/")
    Call<ArrayList<Berita>> getAllBerita(@Header("Authorization") String header);

    @GET("/berita/search")
    Call<ArrayList<Berita>> getAllBeritaByTitle(@Header("Authorization") String header, @Query("title") String title);

}

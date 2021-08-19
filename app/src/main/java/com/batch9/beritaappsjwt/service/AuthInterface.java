package com.batch9.beritaappsjwt.service;

import com.batch9.beritaappsjwt.entity.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthInterface {
    @POST("/login")
    Call<String> loginUser(@Body User user);

    @POST("/register")
    Call<String> daftarUser(@Body User user);
}

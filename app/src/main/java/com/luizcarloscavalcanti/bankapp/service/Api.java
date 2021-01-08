package com.luizcarloscavalcanti.bankapp.service;

import com.luizcarloscavalcanti.bankapp.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(
            @Field("user") String user,
            @Field("password") String password
    );
}

package com.luizcarloscavalcanti.bankapp.service;

import com.luizcarloscavalcanti.bankapp.model.LoginResponse;
import com.luizcarloscavalcanti.bankapp.model.StatementsResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(
            @Field("user") String user,
            @Field("password") String password
    );

    @FormUrlEncoded
    @GET("statements/{userId}")
    Call<StatementsResponse> statements(
            @Path("userId") int userId);

}

package com.luizcarloscavalcanti.bankapp.services;

import com.luizcarloscavalcanti.bankapp.models.LoginModel;
import com.luizcarloscavalcanti.bankapp.models.StatementModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @FormUrlEncoded
    @POST("login")
    Call<LoginModel> postLogin(
            @Field("user") String user,
            @Field("password") String password
    );

    @GET("statements/{userId}")
    Call<StatementModel> getStatementList(
            @Path("userId") int userId);

}

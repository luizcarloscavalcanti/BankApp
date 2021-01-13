package com.luizcarloscavalcanti.bankapp.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;
    private static RetrofitClient retrofitClient;

    private RetrofitClient() {
        String BASE_URL = "https://bank-app-test.herokuapp.com/api/";
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitClient getRetrofitInstance() {
        if (retrofitClient == null) {
            retrofitClient = new RetrofitClient();
        }
        return retrofitClient;
    }

    public ApiService getApiService(){
        return retrofit.create(ApiService.class);
    }
}
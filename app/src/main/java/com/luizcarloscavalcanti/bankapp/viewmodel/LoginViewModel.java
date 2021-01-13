package com.luizcarloscavalcanti.bankapp.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.luizcarloscavalcanti.bankapp.models.LoginModel;
import com.luizcarloscavalcanti.bankapp.models.UserAccountModel;
import com.luizcarloscavalcanti.bankapp.services.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class LoginViewModel extends ViewModel {

    private final MutableLiveData<UserAccountModel> userAccount;

    public LoginViewModel() {
        userAccount = new MutableLiveData<>();
    }

    public MutableLiveData<UserAccountModel> getStatementList() {
        return userAccount;
    }

    public void apiCall(String user, String password) {

        Call<LoginModel> call = RetrofitClient.getRetrofitInstance()
                .getApiService()
                .postLogin(user, password);

        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if(response.isSuccessful()){
                    userAccount.postValue(response.body().getUserAccount());
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                userAccount.postValue(null);
                Log.e(TAG, " onThrowable: " + t.getLocalizedMessage());
            }
        });
    }
}

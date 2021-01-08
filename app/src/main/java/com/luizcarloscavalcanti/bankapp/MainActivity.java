package com.luizcarloscavalcanti.bankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.luizcarloscavalcanti.bankapp.model.LoginResponse;
import com.luizcarloscavalcanti.bankapp.service.RetrofitClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextUser, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUser = findViewById(R.id.editTextUser);
        editTextPassword = findViewById(R.id.editTextPassword);
        findViewById(R.id.buttonLogin).setOnClickListener(this);
    }

    private void efetuarLogin() {
        String user = editTextUser.getText().toString();
        String password = editTextPassword.getText().toString();

        Call<LoginResponse> call = RetrofitClient.getRetrofitInstance()
                .getApi()
                .login(user, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonLogin:
                efetuarLogin();
                break;
        }
    }

}
package com.luizcarloscavalcanti.bankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.luizcarloscavalcanti.bankapp.model.LoginResponse;
import com.luizcarloscavalcanti.bankapp.service.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static String CPF_REGEX = "(^[0-9]+$)";
    private static String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static String PASSWORD_REGEX = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";

    EditText editTextUser, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUser = findViewById(R.id.editTextUser);
        editTextPassword = findViewById(R.id.editTextPassword);
        findViewById(R.id.buttonLogin).setOnClickListener(this);
    }

    private void submitLogin() {
        String user = editTextUser.getText().toString();
        String password = editTextPassword.getText().toString();

        Call<LoginResponse> call = RetrofitClient.getRetrofitInstance()
                .getApi()
                .login(user, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                if (validatePassword(password) && validateUser(user)) {
                    Toast.makeText(MainActivity.this, R.string.loginSuccess, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, R.string.loginError, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {}
        });
    }

    public static boolean validateUser(String user) {
        boolean validateCPF = user
                .matches(CPF_REGEX) && user.length() == 11;

        boolean validateEmail = user
                .matches(EMAIL_REGEX);

        return validateCPF || validateEmail;
    }

    public static boolean validatePassword(String password) {
        boolean validatePassword = password
                .matches(PASSWORD_REGEX);

        return validatePassword;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonLogin:
                submitLogin();
                break;
        }
    }

}
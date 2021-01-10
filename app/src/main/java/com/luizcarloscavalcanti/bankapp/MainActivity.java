package com.luizcarloscavalcanti.bankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.luizcarloscavalcanti.bankapp.model.LoginResponse;
import com.luizcarloscavalcanti.bankapp.service.RetrofitClient;
import com.luizcarloscavalcanti.bankapp.service.LoginSessionManager;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextUser, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUser = findViewById(R.id.editTextUser);
        editTextPassword = findViewById(R.id.editTextPassword);
        findViewById(R.id.buttonLogin).setOnClickListener(this);

        LoginSessionManager loginSessionManager = new LoginSessionManager(MainActivity.this);
        HashMap<String, String> loginDetails = loginSessionManager.getLoginDetail();

        editTextUser.setText(loginDetails.get(LoginSessionManager.USER));

        if (loginSessionManager.checkLogin()) {
            Intent intent = new Intent(MainActivity.this, StatementsActivity.class);
            startActivity(intent);
            this.finish();
        }
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
                    assert loginResponse != null;
                    String userId = loginResponse.getUserAccount().getUserId();
                    String userName = loginResponse.getUserAccount().getName();
                    String userAgency = loginResponse.getUserAccount().getAgency();
                    String userBankAccount = loginResponse.getUserAccount().getBankAccount();
                    String userBalance = String.valueOf(loginResponse.getUserAccount().getBalance());

                    LoginSessionManager loginSessionManager = new LoginSessionManager(MainActivity.this);
                    loginSessionManager.createLoginSession(userId, user, userName, userAgency, userBankAccount, userBalance, password);

                    Intent intent = new Intent(MainActivity.this, StatementsActivity.class);
                    startActivity(intent);

                    MainActivity.this.finish();
                } else {
                    Toast.makeText(MainActivity.this, R.string.loginError, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {}
        });
    }

    public static boolean validateUser(String user) {

        String CPF_REGEX = "(^[0-9]+$)";
        String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        boolean validateCPF = user.matches(CPF_REGEX) && user.length() == 11;
        boolean validateEmail = user.matches(EMAIL_REGEX);

        return validateCPF || validateEmail;
    }

    public static boolean validatePassword(String password) {

        String PASSWORD_REGEX = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";

        return password.matches(PASSWORD_REGEX);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.buttonLogin) {
            submitLogin();
        }
    }

}
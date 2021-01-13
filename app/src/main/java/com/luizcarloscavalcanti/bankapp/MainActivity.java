package com.luizcarloscavalcanti.bankapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.luizcarloscavalcanti.bankapp.utils.SessionManager;
import com.luizcarloscavalcanti.bankapp.utils.ValidateLogin;
import com.luizcarloscavalcanti.bankapp.viewmodel.LoginViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextUser, editTextPassword;
    private TextView textLoginError;
    private SessionManager sessionManager;
    private LoginViewModel viewModel;
    private ValidateLogin validateLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUser = findViewById(R.id.editTextUser);
        editTextPassword = findViewById(R.id.editTextPassword);
        textLoginError = findViewById(R.id.textLoginError);
        findViewById(R.id.buttonLogin).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        sessionManager = new SessionManager(this);
        boolean isUserLoggedIn = sessionManager.checkLogin();

        editTextUser.setText(sessionManager.getUserInfo("user"));

        if(isUserLoggedIn) { moveToStatementActivity(); }
    }

    private void login() {
        validateLogin = new ValidateLogin();
        String user = editTextUser.getText().toString();
        String password = editTextPassword.getText().toString();

        if(validateLogin.isValidLogin(user, password)) {
            viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
            viewModel.getStatementList().observe(this, userAccountModel -> {
                Integer userId = userAccountModel.getUserId();
                String userName = userAccountModel.getName();
                String bankAgency = userAccountModel.getAgency();
                String bankAccount = userAccountModel.getBankAccount();
                Float bankBalance = userAccountModel.getBalance();

                sessionManager.createSession(user, password, userId, userName, bankAgency, bankAccount, bankBalance);

                moveToStatementActivity();
            });
            viewModel.apiCall(user, password);

        } else {
            textLoginError.setVisibility(View.VISIBLE);
            textLoginError.setText(R.string.loginError);
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.buttonLogin) {
            login();
        }
    }

    private void moveToStatementActivity() {
        Intent intent = new Intent(getApplicationContext(), StatementActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }
}
package com.luizcarloscavalcanti.bankapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.luizcarloscavalcanti.bankapp.adapter.StatementAdapter;
import com.luizcarloscavalcanti.bankapp.model.StatementList;
import com.luizcarloscavalcanti.bankapp.model.StatementsResponse;
import com.luizcarloscavalcanti.bankapp.service.Api;
import com.luizcarloscavalcanti.bankapp.service.LoginSessionManager;
import com.luizcarloscavalcanti.bankapp.service.RetrofitClient;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StatementsActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    StatementAdapter statementAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement_list);

        LoginSessionManager loginSessionManager = new LoginSessionManager(StatementsActivity.this);
        HashMap<String, String> loginDetails = loginSessionManager.getLoginDetail();

        String userId =  loginDetails.get(loginSessionManager.USER_ID);
        String userName =  loginDetails.get(loginSessionManager.USER_NAME);
        String userAgency =  loginDetails.get(loginSessionManager.USER_AGENCY);
        String userBankAccount =  loginDetails.get(loginSessionManager.USER_BANK_ACCOUNT);
        String userBankBalance =  loginDetails.get(loginSessionManager.USER_BALANCE);

        TextView textName = findViewById(R.id.textNameUser);
        TextView textAccount = findViewById(R.id.textAccount);
        TextView textBalance = findViewById(R.id.textBalance);
        recyclerView = findViewById(R.id.recentsRecyclerView);
        statementAdapter = new StatementAdapter(this);
        recyclerView.setAdapter(statementAdapter);
        findViewById(R.id.buttonLogout).setOnClickListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        textName.setText(userName);
        textAccount.setText(userBankAccount + " / " + userAgency);

        Locale ptBR = new Locale("pt", "BR");
        String convertedBalance = NumberFormat.getCurrencyInstance(ptBR).format(Float.parseFloat(userBankBalance));
        textBalance.setText(convertedBalance);

        submitLogin(Integer.parseInt(userId));
    }

    private void submitLogin(int userId) {

        Call<StatementsResponse> call = RetrofitClient.getRetrofitInstance()
                .getApi()
                .getStatementsList(userId);

        call.enqueue(new Callback<StatementsResponse>() {
            @Override
            public void onResponse(Call<StatementsResponse> call, Response<StatementsResponse>response) {
                if(response.isSuccessful()){
                StatementsResponse statementsResponse = response.body();
                List<StatementList> statementList = statementsResponse.getStatementList();

                statementAdapter.addStatementList(statementList);

                }
            }

            @Override
            public void onFailure(Call<StatementsResponse> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });

    }

    @Override
    public void onClick(View view) {
        LoginSessionManager loginSessionManager = new LoginSessionManager(StatementsActivity.this);

        switch (view.getId()) {
            case R.id.buttonLogout:
                loginSessionManager.logout();
                Intent intent = new Intent(StatementsActivity.this, MainActivity.class);
                startActivity(intent);
                this.finish();
                break;
        }
    }
}
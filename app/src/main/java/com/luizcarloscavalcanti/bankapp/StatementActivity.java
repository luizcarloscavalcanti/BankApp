package com.luizcarloscavalcanti.bankapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.luizcarloscavalcanti.bankapp.adapter.StatementListAdapter;
import com.luizcarloscavalcanti.bankapp.utils.SessionManager;
import com.luizcarloscavalcanti.bankapp.viewmodel.StatementListViewModel;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;

public class StatementActivity extends AppCompatActivity implements View.OnClickListener {

    private StatementListAdapter adapter;
    private StatementListViewModel viewModel;
    private TextView textName, textAccount, textBalance;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement_list);

        textName = findViewById(R.id.textNameUser);
        textAccount = findViewById(R.id.textAccount);
        textBalance = findViewById(R.id.textBalance);
        findViewById(R.id.buttonLogout).setOnClickListener(this);
        recyclerView = findViewById(R.id.recentsRecyclerView);
    }

    @Override
    protected void onStart() {
        super.onStart();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new StatementListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        viewModel = ViewModelProviders.of(this).get(StatementListViewModel.class);
        viewModel.getStatementList().observe(this, statementListModels -> {
            if(statementListModels != null) {
                adapter.addStatementList(statementListModels);
            }
        });

        isUserLogged();
    }

    private void isUserLogged() {
        SessionManager sessionManager = new SessionManager(this);

        if(sessionManager.checkLogin()){
            Integer userID = sessionManager.getUserId();
            textName.setText(sessionManager.loadUserInfos("userName"));
            textAccount.setText(sessionManager.loadUserInfos("userAccount") +
                    " / " + sessionManager.loadUserInfos("userAgency"));
            textBalance.setText(formatReal(sessionManager.getUserBalance()));

            viewModel.apiCall(userID);
        } else {
            moveToMainActivity();
        }
    }

    @Override
    public void onClick(View view) {
        SessionManager sessionManager = new SessionManager(this);

        if (view.getId() == R.id.buttonLogout) {
            sessionManager.logout();
            moveToMainActivity();
        }
    }

    public String formatReal(Float bankBalance) {
        Locale ptBr = new Locale("pt", "BR");
        return NumberFormat.getCurrencyInstance(ptBr).format(bankBalance);
    }

    private void moveToMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }
}
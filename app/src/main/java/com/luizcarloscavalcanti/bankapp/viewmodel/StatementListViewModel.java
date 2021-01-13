package com.luizcarloscavalcanti.bankapp.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.luizcarloscavalcanti.bankapp.models.StatementListModel;
import com.luizcarloscavalcanti.bankapp.models.StatementModel;
import com.luizcarloscavalcanti.bankapp.services.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class StatementListViewModel extends ViewModel {

    private final MutableLiveData<List<StatementListModel>> statementList;

    public StatementListViewModel() {
        statementList = new MutableLiveData<>();
    }

    public MutableLiveData<List<StatementListModel>> getStatementList() {
        return statementList;
    }

    public void apiCall(int userId) {

        Call<StatementModel> call = RetrofitClient.getRetrofitInstance()
                .getApiService()
                .getStatementList(userId);

        call.enqueue(new Callback<StatementModel>() {
            @Override
            public void onResponse(Call<StatementModel> call, Response<StatementModel> response) {
                if(response.isSuccessful()){
                    assert response.body() != null;
                    statementList.postValue(response.body().getStatementList());
                }
            }

            @Override
            public void onFailure(Call<StatementModel> call, Throwable t) {
                statementList.postValue(null);
                Log.e(TAG, " onThrowable: " + t.getLocalizedMessage());
            }
        });

    }

}

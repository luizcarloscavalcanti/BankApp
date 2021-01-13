package com.luizcarloscavalcanti.bankapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StatementModel {

        @SerializedName("statementList")
        @Expose
        private List<StatementListModel> statementList = null;
        @SerializedName("error")
        @Expose
        private ErrorModel error;

        public List<StatementListModel> getStatementList() {
            return statementList;
        }

        public ErrorModel getError() {
            return error;
        }

}

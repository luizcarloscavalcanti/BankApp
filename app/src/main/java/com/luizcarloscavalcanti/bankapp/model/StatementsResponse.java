package com.luizcarloscavalcanti.bankapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StatementsResponse {

        @SerializedName("statementList")
        @Expose
        private List<StatementList> statementList = null;
        @SerializedName("error")
        @Expose
        private Error error;

        public List<StatementList> getStatementList() {
            return statementList;
        }

        public Error getError() {
            return error;
        }

}

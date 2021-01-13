package com.luizcarloscavalcanti.bankapp.models;

public class ErrorModel {
    private String code, message;

    public ErrorModel(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

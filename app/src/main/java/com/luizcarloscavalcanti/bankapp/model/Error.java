package com.luizcarloscavalcanti.bankapp.model;

public class Error {
    private String code, message;

    public Error(String code, String message) {
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

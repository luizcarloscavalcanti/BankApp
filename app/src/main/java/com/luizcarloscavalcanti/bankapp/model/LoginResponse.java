package com.luizcarloscavalcanti.bankapp.model;

public class LoginResponse {

    private UserAccount userAccount;
    private Error error;

    public LoginResponse(UserAccount userAccount, Error error) {
        this.userAccount = userAccount;
        this.error = error;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public Error getError() {
        return error;
    }

}

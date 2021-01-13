package com.luizcarloscavalcanti.bankapp.models;

public class LoginModel {

    private UserAccountModel userAccount;
    private ErrorModel error;

    public LoginModel(UserAccountModel userAccount, ErrorModel error) {
        this.userAccount = userAccount;
        this.error = error;
    }

    public UserAccountModel getUserAccount() {
        return userAccount;
    }

    public ErrorModel getError() { return error; }

}

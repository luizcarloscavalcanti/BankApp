package com.luizcarloscavalcanti.bankapp.model;

public class UserAccount {

    private String userId, name;

    public UserAccount(String userId, String name){
        this.userId = userId;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

}

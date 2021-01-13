package com.luizcarloscavalcanti.bankapp.models;

public class UserAccountModel {

    private Integer userId;
    private String name, agency, bankAccount;
    private Float balance;

    public UserAccountModel(Integer userId, String name, String bankAccount, String agency, Float balance) {
        this.name = name;
        this.userId = userId;
        this.bankAccount = bankAccount;
        this.agency = agency;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public String getAgency() {
        return agency;
    }

    public Float getBalance() {
        return balance;
    }
}

package com.luizcarloscavalcanti.bankapp.model;

public class UserAccount {

    private String name, userId, agency, bankAccount;
    private Float balance;

    public UserAccount(String name, String userId, String bankAccount, String agency, Float balance) {
        this.name = name;
        this.userId = userId;
        this.bankAccount = bankAccount;
        this.agency = agency;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
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

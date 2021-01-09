package com.luizcarloscavalcanti.bankapp.model;

public class UserAccount {

    private String name;
    private Integer userId;
    private Float balance;
    private Long agency, bankAccount;

    public UserAccount(String name, Integer userId, Long bankAccount, Long agency, Float balance) {
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

    public Long getBankAccount() {
        return bankAccount;
    }

    public Long getAgency() {
        return agency;
    }

    public Float getBalance() {
        return balance;
    }
}

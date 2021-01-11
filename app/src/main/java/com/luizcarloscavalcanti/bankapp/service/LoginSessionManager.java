package com.luizcarloscavalcanti.bankapp.service;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class LoginSessionManager {

    SharedPreferences loginSession;
    SharedPreferences.Editor editor;
    Context context;

    public static final String IS_LOGIN = "isLogged";
    public static final String SHARED_PREFS = "savedLogin";
    public static final String USER_ID = "userId";
    public static final String USER = "user";
    public static final String USER_NAME = "userName";
    public static final String USER_AGENCY = "userAgency";
    public static final String USER_BANK_ACCOUNT = "userBankAccount";
    public static final String USER_BALANCE = "userBalance";
    public static final String PASSWORD = "password";

    public LoginSessionManager(Context _context) {
        context = _context;
        loginSession = _context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        editor = loginSession.edit();
    }

    public void createLoginSession(String userId, String user, String userName, String userAgency, String userBankAccount, String userBalance, String password) {
        String cryptPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());

        editor.putBoolean(IS_LOGIN, true);

        editor.putString(USER_ID, userId);
        editor.putString(USER, user);
        editor.putString(USER_NAME, userName);
        editor.putString(USER_AGENCY, userAgency);
        editor.putString(USER_BANK_ACCOUNT, userBankAccount);
        editor.putString(USER_BALANCE, userBalance);
        editor.putString(PASSWORD, cryptPassword);

        editor.commit();
    }

    public HashMap<String, String> getLoginDetail(){
        HashMap<String, String> loginData = new HashMap<String, String>();

        loginData.put(USER_ID, loginSession.getString(USER_ID, null));
        loginData.put(USER, loginSession.getString(USER, null));
        loginData.put(USER_NAME, loginSession.getString(USER_NAME, null));
        loginData.put(USER_AGENCY, loginSession.getString(USER_AGENCY, null));
        loginData.put(USER_BANK_ACCOUNT, loginSession.getString(USER_BANK_ACCOUNT, null));
        loginData.put(USER_BALANCE, loginSession.getString(USER_BALANCE, null));
        loginData.put(PASSWORD, loginSession.getString(PASSWORD, null));

        return loginData;
    }

    public boolean checkLogin() {
        if(loginSession.getBoolean(IS_LOGIN, false)){
            return true;
        } else {
            return false;
        }
    }

    public void logout() {
        editor.putBoolean(IS_LOGIN, false);
        editor.commit();
    }
}

package com.luizcarloscavalcanti.bankapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.luizcarloscavalcanti.bankapp.models.LoginModel;
import com.luizcarloscavalcanti.bankapp.models.UserAccountModel;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class SessionManager {

    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    public static final String SHARED_PREFS = "session";
    public static final String IS_LOGIN = "isLogged";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    public static final String USER_ID = "userId";
    public static final String USER_NAME = "userName";
    public static final String USER_AGENCY = "userAgency";
    public static final String USER_ACCOUNT = "userAccount";
    public static final String USER_BALANCE = "userBalance";

    public SessionManager(Context _context) {
        sharedPreferences = _context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String user, String password, Integer userId, String userName, String userAgency, String userAccount, Float userBalance) {
        String passwordCrypt = BCrypt.withDefaults().hashToString(12, password.toCharArray());

        editor.putString(USER, user);
        editor.putString(PASSWORD, passwordCrypt);
        editor.putInt(USER_ID, userId);
        editor.putString(USER_NAME, userName);
        editor.putString(USER_AGENCY, userAgency);
        editor.putString(USER_ACCOUNT, userAccount);
        editor.putFloat(USER_BALANCE, userBalance);
        editor.putBoolean(IS_LOGIN, true);

        editor.commit();
    }

    public Integer getUserId(){
        return sharedPreferences.getInt(USER_ID, 0);
    }

    public String getUserInfo(String name){
        return sharedPreferences.getString(name, null);
    }

    public String getUserBalance(){
            Locale ptBr = new Locale("pt", "BR");
            return NumberFormat.getCurrencyInstance(ptBr).format(sharedPreferences.getFloat(USER_BALANCE, 0));
    }

    public boolean checkLogin(){
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }

    public void logout() {
        editor.putBoolean(IS_LOGIN, false);
        editor.commit();
    }
}

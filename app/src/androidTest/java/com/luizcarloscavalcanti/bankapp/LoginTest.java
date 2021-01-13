package com.luizcarloscavalcanti.bankapp;

import com.luizcarloscavalcanti.bankapp.utils.ValidateLogin;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

public class LoginTest {

    private static final String CORRECT_PASSWORD = "123@Qwer";
    private static final String PASSWORD_INCORRECT_LENGTH = "123@Qwe";
    private static final String PASSWORD_NO_SYMBOLS = "123Qwert";

    private static final String USER_CPF = "12312312301";
    private static final String USER_EMAIL = "email@email.com";
    private static final String INCORRECT_USER = "user";

    ValidateLogin validateLogin = new ValidateLogin();

    @Test
    public void CorrectLoginCPF() {
        boolean isValidLogin = validateLogin.isValidLogin(USER_CPF, CORRECT_PASSWORD);
        Assert.assertTrue(isValidLogin);
    }

    @Test
    public void CorrectLoginEmail() {
        boolean isValidLogin = validateLogin.isValidLogin(USER_EMAIL, CORRECT_PASSWORD);
        Assert.assertTrue(isValidLogin);
    }

    @Test
    public void IncorrectLoginPassword() {
        boolean isValidLogin = validateLogin.isValidLogin(USER_CPF, PASSWORD_INCORRECT_LENGTH);
        Assert.assertFalse(isValidLogin);
    }

    @Test
    public void IncorrectLoginUser() {
        boolean isValidLogin = validateLogin.isValidLogin(INCORRECT_USER, CORRECT_PASSWORD);
        Assert.assertFalse(isValidLogin);
    }

    @Test
    public void IncorrectLoginPasswordSymbols() {
        boolean isValidLogin = validateLogin.isValidLogin(USER_CPF, PASSWORD_NO_SYMBOLS);
        Assert.assertFalse(isValidLogin);
    }

}
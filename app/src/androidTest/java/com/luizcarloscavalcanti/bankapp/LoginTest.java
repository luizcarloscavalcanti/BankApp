package com.luizcarloscavalcanti.bankapp;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

public class LoginTest {

    private static final String CORRECT_PASSWORD = "123@Qwer";
    private static final String PASSWORD_INCORRECT_LENGTH = "123@Qwe";
    private static final String PASSWORD_NO_ALPHANUMERIC = "@Qwertyui";
    private static final String PASSWORD_NO_SYMBOLS = "123Qwert";
    private static final String PASSWORD_NO_LOWERCASE = "123QWE@#";
    private static final String PASSWORD_NO_UPPERCASE = "123qwe@#";

    private static final String USER_CPF = "12312312301";
    private static final String USER_EMAIL = "email@email.com";
    private static final String INCORRECT_USER = "user";
    private static final String INCORRECT_EMAIL = "email@email.com.";
    private static final String INCORRECT_CPF_LENGTH = "123123123";

    @Test
    public void CorrectPassword() {
        boolean validatePassword = MainActivity.validatePassword(CORRECT_PASSWORD);
        Assert.assertTrue(validatePassword);
    }

    @Test
    public void PasswordIncorrectLength() {
        boolean validatePassword = MainActivity.validatePassword(PASSWORD_INCORRECT_LENGTH);
        Assert.assertFalse(validatePassword);
    }

    @Test
    public void PasswordNoAlphanumeric() {
        boolean validatePassword = MainActivity.validatePassword(PASSWORD_NO_ALPHANUMERIC);
        Assert.assertFalse(validatePassword);
    }

    @Test
    public void PasswordNoSymbols() {
        boolean validatePassword = MainActivity.validatePassword(PASSWORD_NO_SYMBOLS);
        Assert.assertFalse(validatePassword);
    }

    @Test
    public void PasswordNoLowerCase() {
        boolean validatePassword = MainActivity.validatePassword(PASSWORD_NO_LOWERCASE);
        Assert.assertFalse(validatePassword);
    }

    @Test
    public void PasswordNoUpperCase() {
        boolean validatePassword = MainActivity.validatePassword(PASSWORD_NO_UPPERCASE);
        Assert.assertFalse(validatePassword);
    }

    @Test
    public void LoginUserCpf() {
        boolean validateUser = MainActivity.validateUser(USER_CPF);
        Assert.assertTrue(validateUser);
    }

    @Test
    public void LoginUserEmail() {
        boolean validateUser = MainActivity.validateUser(USER_EMAIL);
        Assert.assertTrue(validateUser);
    }

    @Test
    public void LoginIncorrectUser() {
        boolean validateUser = MainActivity.validateUser(INCORRECT_USER);
        Assert.assertFalse(validateUser);
    }

    @Test
    public void LoginIncorrectEmail() {
        boolean validateUser = MainActivity.validateUser(INCORRECT_EMAIL);
        Assert.assertFalse(validateUser);
    }

    @Test
    public void LoginIncorrectCpfLength() {
        boolean validateUser = MainActivity.validateUser(INCORRECT_CPF_LENGTH);
        Assert.assertFalse(validateUser);
    }

}
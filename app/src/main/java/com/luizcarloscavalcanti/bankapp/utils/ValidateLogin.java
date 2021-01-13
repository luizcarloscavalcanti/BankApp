package com.luizcarloscavalcanti.bankapp.utils;

public class ValidateLogin {

    public boolean isValidLogin(String user, String password) {

        String CPF_REGEX = "(^[0-9]+$)";
        String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        String PASSWORD_REGEX = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";

        boolean validateUser = (user.matches(CPF_REGEX) && user.length() == 11)
                || user.matches(EMAIL_REGEX);
        boolean validatePassword = password.matches(PASSWORD_REGEX);

        return validateUser && validatePassword ;
    }
}

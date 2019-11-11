package com.example.virtualwallets.loginComponent.model;

import android.util.Patterns;

public class Login {

    private ILoginService loginService;

    public Login(ILoginService loginService) {
        this.loginService = loginService;
    }

    public void performLogin(String email, String password, ILoginCallback callback) {
        boolean loginSuccess = loginService.performLogin(email, password);
        if (loginSuccess) {
            callback.onLoginSuccess();
        } else {
            callback.onLoginError();
        }
    }

    public boolean areUserCredentialsValid(String email, String password){
        if (!isUserNameValid(email)) {
            return  false;
        } else if (!isPasswordValid(password)) {
            return  false;
        } else {
            return  true;
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}

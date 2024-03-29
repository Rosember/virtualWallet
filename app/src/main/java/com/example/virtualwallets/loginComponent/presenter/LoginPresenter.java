package com.example.virtualwallets.loginComponent.presenter;

import android.util.Patterns;


import com.example.virtualwallets.loginComponent.model.ILoginPersistence;
import com.example.virtualwallets.loginComponent.model.LoginServiceImplement;
import com.example.virtualwallets.loginComponent.view.ILoginView;

public class LoginPresenter implements ILoginPresenter{

    private ILoginView view;
    private ILoginPersistence login;

    public LoginPresenter(ILoginView view) {
        this.view = view;
        this.login = new LoginServiceImplement(this);
    }

    public void onLoginButtonPressed(String email, String password) {
        if (!areUserCredentialsValid(email, password)) {
            view.showInvalidCredentialsMessage();
            return;
        }

        login.validateCredentials(email, password);

    }

    @Override
    public void onLoginSucces() {
        view.showLoginSuccessMessage();
    }

    @Override
    public void onLoginInvalidCredentials() {
        view.showInvalidCredentialsMessage();
    }

    @Override
    public void onNetworkError() {
        view.showNetworkErrorMessage();
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

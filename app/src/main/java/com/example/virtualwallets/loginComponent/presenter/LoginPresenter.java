package com.example.virtualwallets.loginComponent.presenter;

import com.example.virtualwallets.loginComponent.model.ILoginCallback;
import com.example.virtualwallets.loginComponent.model.Login;
import com.example.virtualwallets.loginComponent.view.ILoginView;

public class LoginPresenter {

    private ILoginView view;
    private Login login;

    public LoginPresenter(ILoginView view, Login login) {
        this.view = view;
        this.login = login;
    }

    public void onLoginButtonPressed(String email, String password) {
        if (!login.areUserCredentialsValid(email, password)) {
            view.showInvalidCredentialsMessage();
            return;
        }

        login.performLogin(email, password, new ILoginCallback() {
            @Override
            public void onLoginSuccess() {
                view.showLoginSuccessMessage();
            }

            @Override
            public void onLoginError() {
                view.showNetworkErrorMessage();
            }
        });

    }
}

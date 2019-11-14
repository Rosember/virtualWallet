package com.example.virtualwallets.loginComponent.presenter;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-13
 */
public interface ILoginPresenter {
    void onLoginSucces();
    void onLoginInvalidCredentials();
    void onNetworkError();
}

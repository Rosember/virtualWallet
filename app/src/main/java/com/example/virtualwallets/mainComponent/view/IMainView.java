package com.example.virtualwallets.mainComponent.view;

import com.example.virtualwallets.transferComponent.model.Wallets;

import java.util.List;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-11
 */
public interface IMainView {

    void logout();
    void logoutSuccess();
    void onLoad();
    void onLoadSuccess(List<Wallets> wallets);
    void onStopRefreshSwipeWallet();
    void onStartRefreshSwipeWallet();
}

package com.example.virtualwallets.mainComponent.presenter;

import com.example.virtualwallets.POJOS.Wallets;
import com.example.virtualwallets.mainComponent.model.WalletsResponse;

import java.util.List;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-11
 */
public interface IMainPresenter {
    void onResume();
    void onLogout();
    void onLogoutSucces();
    void onLoadWallets();
    void onLoadWalletsSuccess(List<Wallets> listWallet);
}

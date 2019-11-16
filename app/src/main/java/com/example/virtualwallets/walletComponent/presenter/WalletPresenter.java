package com.example.virtualwallets.walletComponent.presenter;

import com.example.virtualwallets.transferComponent.model.Wallets;
import com.example.virtualwallets.loginComponent.model.LogoutServiceImplement;
import com.example.virtualwallets.walletComponent.model.IListWalletService;
import com.example.virtualwallets.walletComponent.model.ListWalletService;
import com.example.virtualwallets.walletComponent.view.IWalletView;
import com.example.virtualwallets.utils.OnServiceResponse;

import java.util.List;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-11
 */
public class WalletPresenter implements IWalletPresenter, OnServiceResponse<List<Wallets>> {

    private IWalletView view;
    private IListWalletService model;

    public WalletPresenter(IWalletView view) {
        this.view = view;
        this.model = new ListWalletService(this);

    }

    @Override
    public void onDestroy() {
        model.onDestroy();
    }

    @Override
    public void onLogout() {
        new LogoutServiceImplement(new OnServiceResponse<Boolean>() {
            @Override
            public void onComplet(Boolean result) {
                view.logoutSuccess();
            }

            @Override
            public void onError() {

            }
        }).onLogout();
    }


    @Override
    public void onLoadWallets() {
        model.onLoadWallets();
    }


    @Override
    public void onComplet(List<Wallets> result) {
        view.onLoadSuccess(result);
    }

    @Override
    public void onError() {
        // para la vista enviar el error
    }
}

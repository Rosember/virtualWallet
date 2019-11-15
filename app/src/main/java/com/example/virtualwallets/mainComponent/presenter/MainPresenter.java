package com.example.virtualwallets.mainComponent.presenter;

import com.example.virtualwallets.transferComponent.model.Wallets;
import com.example.virtualwallets.loginComponent.model.LogoutServiceImplement;
import com.example.virtualwallets.mainComponent.model.IListWalletService;
import com.example.virtualwallets.mainComponent.model.ListWalletService;
import com.example.virtualwallets.mainComponent.view.IMainView;
import com.example.virtualwallets.utils.OnServiceResponse;

import java.util.List;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-11
 */
public class MainPresenter implements IMainPresenter, OnServiceResponse<List<Wallets>> {

    private IMainView view;
    private IListWalletService model;

    public MainPresenter(IMainView view) {
        this.view = view;
        this.model = new ListWalletService(this);

    }

    @Override
    public void onResume() {
        model.onResume();
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

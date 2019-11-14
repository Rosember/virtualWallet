package com.example.virtualwallets.mainComponent.presenter;

import com.example.virtualwallets.POJOS.Wallets;
import com.example.virtualwallets.mainComponent.model.IMainModel;
import com.example.virtualwallets.mainComponent.model.MainModel;
import com.example.virtualwallets.mainComponent.model.WalletsResponse;
import com.example.virtualwallets.mainComponent.view.IMainView;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-11
 */
public class MainPresenter implements IMainPresenter {

    private IMainView view;
    private IMainModel model;

    public MainPresenter(IMainView view) {
        this.view = view;
        this.model = new MainModel(this);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onLogout() {
        model.logout();
    }

    @Override
    public void onLogoutSucces() {
        view.logoutSuccess();
    }

    @Override
    public void onLoadWallets() {
        model.onLoadWallets();
    }

    @Override
    public void onLoadWalletsSuccess(List<Wallets> listWallet) {
        view.onLoadSuccess(listWallet);
    }
}

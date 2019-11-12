package com.example.virtualwallets.mainComponent.model;

import com.example.virtualwallets.mainComponent.presenter.IMainPresenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-12
 */
public class MainModel implements IMainModel{

    private IMainPresenter presenter;
    private Disposable disposable;

    public MainModel(IMainPresenter presenter) {
        this.presenter = presenter;
        this.disposable = new CompositeDisposable();

    }

    @Override
    public void onLoadWallets() {

    }

    @Override
    public void onResume() {
        disposable.dispose();
    }

}

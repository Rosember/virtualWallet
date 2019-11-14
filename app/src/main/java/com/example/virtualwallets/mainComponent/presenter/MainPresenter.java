package com.example.virtualwallets.mainComponent.presenter;

import com.example.virtualwallets.mainComponent.model.IMainModel;
import com.example.virtualwallets.mainComponent.model.MainModel;
import com.example.virtualwallets.mainComponent.view.IMainView;

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
}

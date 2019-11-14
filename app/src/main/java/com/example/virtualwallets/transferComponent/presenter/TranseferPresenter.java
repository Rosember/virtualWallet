package com.example.virtualwallets.transferComponent.presenter;

import com.example.virtualwallets.mainComponent.model.IMainModel;
import com.example.virtualwallets.mainComponent.model.MainModel;
import com.example.virtualwallets.mainComponent.presenter.IMainPresenter;
import com.example.virtualwallets.transferComponent.model.ITransferModel;
import com.example.virtualwallets.transferComponent.model.TransferModel;
import com.example.virtualwallets.transferComponent.view.ITransferView;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-11
 */
public class TranseferPresenter implements ITransferPresenter,IMainModel{

    private ITransferView view;
    private ITransferModel model;

    private IMainModel mainModel;

    public TranseferPresenter(ITransferView view) {
        this.view = view;
        this.model = new TransferModel(this);
        this.mainModel = new MainModel((IMainPresenter) this);
    }

    @Override
    public void onLoadWallets() {
        mainModel.onLoadWallets();
    }

    @Override
    public void logout() {

    }

    @Override
    public void onResume() {

    }
}

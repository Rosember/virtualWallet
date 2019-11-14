package com.example.virtualwallets.transferComponent.presenter;

import com.example.virtualwallets.transferComponent.model.ITransferModel;
import com.example.virtualwallets.transferComponent.model.TransferModel;
import com.example.virtualwallets.transferComponent.view.ITransferView;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-11
 */
public class TranseferPresenter implements ITransferPresenter{

    private ITransferView view;
    private ITransferModel model;

    public TranseferPresenter(ITransferView view) {
        this.view = view;
        this.model = new TransferModel(this);
    }

    @Override
    public void onResume() {

    }
}

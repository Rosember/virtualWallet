package com.example.virtualwallets.transferComponent.model;

import com.example.virtualwallets.transferComponent.presenter.ITransferPresenter;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-13
 */
public class TransferModel implements ITransferModel {

    private ITransferPresenter presenter;

    public TransferModel(ITransferPresenter presenter) {
        this.presenter = presenter;
    }
}

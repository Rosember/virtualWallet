package com.example.virtualwallets.transferComponent.presenter;

import com.example.virtualwallets.transferComponent.model.TransferRequest;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-11
 */
public interface ITransferPresenter {

    void onLoadWalletCombo();
    void sendTransfer(TransferRequest request);
    void onDestroy();
    void findByNumberWallet(String number);
}

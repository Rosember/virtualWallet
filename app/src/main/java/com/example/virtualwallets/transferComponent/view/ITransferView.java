package com.example.virtualwallets.transferComponent.view;

import com.example.virtualwallets.transferComponent.model.Wallets;

import java.util.List;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-11
 */
public interface ITransferView {

    void startLoading();
    void stopLoading();
    void onsuccess();
    void onErrorLoadWallet();
    void onErrorTransfer();
    void onLoadMyWallet(List<Wallets> walletsList);
}

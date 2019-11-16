package com.example.virtualwallets.transferComponent.model;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-13
 */
public interface ITransferService {

    void onTransfer(TransferRequest request);
    void onDestroy();
}

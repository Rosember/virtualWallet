package com.example.virtualwallets.transferComponent.presenter;

import android.util.Log;

import com.example.virtualwallets.transferComponent.model.CheckWallet;
import com.example.virtualwallets.transferComponent.model.ICheckWallet;
import com.example.virtualwallets.walletComponent.model.ListWalletService;
import com.example.virtualwallets.transferComponent.model.TransferRequest;
import com.example.virtualwallets.transferComponent.model.Wallets;
import com.example.virtualwallets.transferComponent.model.ITransferService;
import com.example.virtualwallets.transferComponent.model.TransferService;
import com.example.virtualwallets.transferComponent.view.ITransferView;
import com.example.virtualwallets.utils.OnServiceResponse;

import java.util.List;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-11
 */
public class TranseferPresenter implements ITransferPresenter, OnServiceResponse {

    private final String TAG = getClass().getSimpleName();
    private ITransferView view;
    private ITransferService model;

    public TranseferPresenter(ITransferView view) {
        this.view = view;
        this.model = new TransferService(this);
    }


    @Override
    public void onLoadWalletCombo() {

        new ListWalletService(new OnServiceResponse<List<Wallets>>() {
            @Override
            public void onComplet(List<Wallets> result) {
                Log.d(TAG, "onComplet: ");
                view.onLoadListWallet(result);
            }

            @Override
            public void onError() {

            }
        }).onLoadWallets();
    }

    @Override
    public void sendTransfer(TransferRequest request) {
        model.onTransfer(request);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void findByNumberWallet(String number) {
        ICheckWallet checkWallet = new CheckWallet();
        checkWallet.findByNumberWallet(number, new OnServiceResponse<Integer>() {
            @Override
            public void onComplet(Integer result) {
                view.onFoundNumber(result);
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void onComplet(Object result) {
        view.onSuccess();
    }

    @Override
    public void onError() {
        Log.d(TAG, "onError: Error en la transaccion ");
        view.onErrorTransfer();
    }
}

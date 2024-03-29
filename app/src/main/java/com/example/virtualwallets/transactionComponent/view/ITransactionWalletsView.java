package com.example.virtualwallets.transactionComponent.view;

import com.example.virtualwallets.transactionComponent.model.DaoTransaction;

import java.util.List;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-11
 */
public interface ITransactionWalletsView {

    void showCurrentBalance(double balance);
    void showTransactions(List<DaoTransaction> transactionList);
    void showNoTransaction(); //no exist transaction
    void showNetworkErrorMessage();
    void showAccount();
}

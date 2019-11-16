package com.example.virtualwallets.transactionComponent.presenter;

import com.example.virtualwallets.transactionComponent.model.DaoTransaction;

import java.util.List;

public interface ITransactionPresenter {
    void Transactions(List<DaoTransaction> transactions);
    void NetworkErrorInTransaction(String message);
}

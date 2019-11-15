package com.example.virtualwallets.transactionComponent.presenter;

import com.example.virtualwallets.transactionComponent.model.DaoTransaction;

import java.util.List;

public interface IBalancePresenter {
    void currentBalance(double balance);
    void NetworkErrorInCurrentBalance(String message);
}

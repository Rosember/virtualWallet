package com.example.virtualwallets.transactionComponent.model;

import com.example.virtualwallets.utils.OnServiceResponse;

import java.util.List;

public class TransactionModel {

    private ITransactionService iTransactionService;

    public TransactionModel(ITransactionService iTransactionService) {

        this.iTransactionService = iTransactionService;
    }

    public void geTransactions(String numberAccount , OnServiceResponse<List<DaoTransaction>> response ){
        iTransactionService.getTransaction(numberAccount , response);
    }

    public void getCurrentBalance(String numberAccount, OnServiceResponse<Double> response) {
        iTransactionService.getCurrentBalanceByNumberAccount(numberAccount, response);
    }
}

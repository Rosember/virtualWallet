package com.example.virtualwallets.transactionComponent.model;

import com.example.virtualwallets.utils.OnServiceResponse;

import java.util.List;

public class TransactionModel {

    private ITransactionService iTransactionService;

    public TransactionModel(ITransactionService iTransactionService) {

        this.iTransactionService = iTransactionService;
    }

    public void geTransactions(String numberAccount , int wallet_id, OnServiceResponse<List<DaoTransaction>> response ){
        iTransactionService.getTransaction(numberAccount , wallet_id , response);
    }

    public void getCurrentBalance( String numberAccount, int wallet_id, OnServiceResponse<Double> response) {
        iTransactionService.getCurrentBalanceByNumberAccount( numberAccount, wallet_id, response);
    }
}

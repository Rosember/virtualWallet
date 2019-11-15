package com.example.virtualwallets.transactionComponent.model;

import java.util.List;

public class TransactionModel {

    private ITransactionService iTransactionService;

    public TransactionModel(ITransactionService iTransactionService) {

        this.iTransactionService = iTransactionService;
    }

    public void geTransactions(String numberAccount){
        iTransactionService.getTransaction(numberAccount);
    }

    public void getCurrentBalance(String numberAccount) {
        iTransactionService.getCurrentBalanceByNumberAccount(numberAccount);
    }
}

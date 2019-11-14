package com.example.virtualwallets.transactionComponent.model;

import java.util.List;

public class TransactionModel {

    private ITransactionService iTransactionService;

    public TransactionModel(ITransactionService iTransactionService) {
        this.iTransactionService = iTransactionService;
    }

    public List<DaoTransaction> geTransactions(String numberAccount){
        List<DaoTransaction> transactionList =  iTransactionService.getTransaction(numberAccount);
        return  transactionList;
    }

    public Double getCurrentBalance(String numberAccount) {
        double currentBalance = iTransactionService.getCurrentBalanceByNumberAccount(numberAccount);
        return currentBalance;
    }
}

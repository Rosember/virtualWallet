package com.example.virtualwallets.transactionComponent.model;

import com.example.virtualwallets.utils.OnServiceResponse;

import java.util.List;

public interface ITransactionService {

    void getTransaction (String numberAccount, int wallet_id, OnServiceResponse<List<DaoTransaction>> response);

    void getCurrentBalanceByNumberAccount(String numberAccount,int wallet_id, OnServiceResponse<Double> response);
}

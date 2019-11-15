package com.example.virtualwallets.transactionComponent.model;

import java.util.List;

public interface ITransactionService {

    void getTransaction (String numberAccount);

    void getCurrentBalanceByNumberAccount(String numberAccount);
}

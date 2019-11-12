package com.example.virtualwallets.transactionComponent.model;

import java.util.List;

public interface ITransactionService {
    List<DaoTransaction> getTransaction (String numberAccount);
}

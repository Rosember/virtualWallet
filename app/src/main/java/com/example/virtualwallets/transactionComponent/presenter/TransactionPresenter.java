package com.example.virtualwallets.transactionComponent.presenter;

import com.example.virtualwallets.transactionComponent.model.DaoTransaction;
import com.example.virtualwallets.transactionComponent.model.TransactionModel;
import com.example.virtualwallets.transactionComponent.model.TransactionServiceImplement;
import com.example.virtualwallets.transactionComponent.view.ITransactionWalletsView;

import java.util.List;

public class TransactionPresenter {

    private ITransactionWalletsView iTransactionWalletsView;
    private TransactionModel transactionModel;

    public TransactionPresenter(ITransactionWalletsView iTransactionWalletsView) {
        this.iTransactionWalletsView = iTransactionWalletsView;
        transactionModel = new TransactionModel(new TransactionServiceImplement());
    }

    public void onGetAllTransactions(String numberAccount){
        List<DaoTransaction> transactionList =  transactionModel.geTransactions(numberAccount);
        if (transactionList!=null && transactionList.size()>0 ){
            iTransactionWalletsView.showAccount();
            iTransactionWalletsView.showTransactions(transactionList);
            return;
        }
        if (transactionList!=null && transactionList.size() == 0){
            iTransactionWalletsView.showAccount();
            iTransactionWalletsView.showNoTransacton();
            return;
        }
        if (transactionList==null){
            iTransactionWalletsView.showNetworkErrorMessage();
            return;
        }

    }
}

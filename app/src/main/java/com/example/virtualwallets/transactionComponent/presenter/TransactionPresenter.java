package com.example.virtualwallets.transactionComponent.presenter;

import com.example.virtualwallets.transactionComponent.model.DaoTransaction;
import com.example.virtualwallets.transactionComponent.model.TransactionModel;
import com.example.virtualwallets.transactionComponent.model.TransactionServiceImplement;
import com.example.virtualwallets.transactionComponent.view.ITransactionWalletsView;

import java.util.List;

public class TransactionPresenter implements ITransactionPresenter,IBalancePresenter {

    private ITransactionWalletsView iTransactionWalletsView;
    private TransactionModel transactionModel;

    public TransactionPresenter(ITransactionWalletsView iTransactionWalletsView) {
        this.iTransactionWalletsView = iTransactionWalletsView;
        transactionModel = new TransactionModel(new TransactionServiceImplement(this,this));
    }

    public void getTransactions(String numberAccount) {
        transactionModel.geTransactions(numberAccount);
    }

    public void getCurrentBalance(String numberAccount) {
        transactionModel.getCurrentBalance(numberAccount);
    }

    @Override
    public void currentBalance(double balance) {
        iTransactionWalletsView.showCurrentBalance(balance);
    }

    @Override
    public void NetworkErrorInCurrentBalance(String message) {
        iTransactionWalletsView.showNetworkErrorMessage();
    }

    @Override
    public void Transactions(List<DaoTransaction> transactions) {
        if (transactions!=null && transactions.size()>0 ){
            iTransactionWalletsView.showAccount();
            iTransactionWalletsView.showTransactions(transactions);
            return;
        }
        if (transactions!=null && transactions.size() == 0){
            iTransactionWalletsView.showAccount();
            iTransactionWalletsView.showNoTransaction();
            return;
        }
        if (transactions==null){
            iTransactionWalletsView.showNetworkErrorMessage();
            return;
        }
    }

    @Override
    public void NetworkErrorInTransaction(String message) {
        iTransactionWalletsView.showNetworkErrorMessage();
    }
}

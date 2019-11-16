package com.example.virtualwallets.transactionComponent.presenter;

import com.example.virtualwallets.transactionComponent.model.DaoTransaction;
import com.example.virtualwallets.transactionComponent.model.TransactionModel;
import com.example.virtualwallets.transactionComponent.model.TransactionServiceImplement;
import com.example.virtualwallets.transactionComponent.view.ITransactionWalletsView;
import com.example.virtualwallets.utils.OnServiceResponse;

import java.util.List;

public class TransactionPresenter  {

    private ITransactionWalletsView iTransactionWalletsView;
    private TransactionModel transactionModel;

    public TransactionPresenter(ITransactionWalletsView iTransactionWalletsView) {
        this.iTransactionWalletsView = iTransactionWalletsView;
        transactionModel = new TransactionModel(new TransactionServiceImplement());
    }

    public void getTransactions(String numberAccount, int wallet_id) {
        transactionModel.geTransactions(numberAccount , wallet_id,  new OnServiceResponse<List<DaoTransaction>>(){

            @Override
            public void onComplet(List<DaoTransaction> result) {
                List<DaoTransaction> transactions = result;
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
            public void onError() {
                iTransactionWalletsView.showNetworkErrorMessage();
            }
        });
    }

    public void getCurrentBalance(String numberAccount, int wallet_id) {
        transactionModel.getCurrentBalance(numberAccount, wallet_id, new OnServiceResponse<Double>() {
            @Override
            public void onComplet(Double result) {
                iTransactionWalletsView.showCurrentBalance(result);
            }

            @Override
            public void onError() {
                iTransactionWalletsView.showNetworkErrorMessage();
            }
        });
    }

}

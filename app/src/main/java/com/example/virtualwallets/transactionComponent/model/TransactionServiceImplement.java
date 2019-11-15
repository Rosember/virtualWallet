package com.example.virtualwallets.transactionComponent.model;

import android.util.Log;

import com.example.virtualwallets.AppBase;
import com.example.virtualwallets.transactionComponent.presenter.IBalancePresenter;
import com.example.virtualwallets.transactionComponent.presenter.ITransactionPresenter;
import com.example.virtualwallets.transactionComponent.presenter.TransactionPresenter;
import com.example.virtualwallets.transactionComponent.view.TransactionWalletsView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TransactionServiceImplement implements ITransactionService {

    private final String TAG = getClass().getSimpleName();

    private IBalancePresenter balancePresenter;
    private ITransactionPresenter transactionPresenter;

    public TransactionServiceImplement(IBalancePresenter balancePresenter, ITransactionPresenter transactionPresenter){
        this.balancePresenter= balancePresenter;
        this.transactionPresenter = transactionPresenter;
    }

    @Override
    public void getTransaction(String numberAccount) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppBase.BASE_URL_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build() ;

        List<DaoTransaction> transactionList = new LinkedList<>();
        SimpleDateFormat dateFormat  =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        try {
            String strDate = "2013-05-15T10:00:00-0700";
            Date dateNew = dateFormat.parse(strDate);

            transactionList.add(new DaoTransaction(dateNew, "Angel Beats", 230));
            transactionList.add(new DaoTransaction(dateNew, "Death Note", -456));
            transactionList.add(new DaoTransaction(dateNew, "Fate Stay Night", 342));
            transactionList.add(new DaoTransaction(dateNew, "Welcome to the NHK", 645));
            transactionList.add(new DaoTransaction(dateNew, "Suzumiya Haruhi", 459));
        } catch (ParseException ex) {
            Log.v("Exception", ex.getLocalizedMessage());
        }



    }

    @Override
    public void getCurrentBalanceByNumberAccount(String numberAccount) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppBase.BASE_URL_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build() ;

         //IBalancePresenter balancePresenter= new TransactionPresenter(new TransactionWalletsView());
         //balancePresenter.currentBalance(954.25);

    }
}

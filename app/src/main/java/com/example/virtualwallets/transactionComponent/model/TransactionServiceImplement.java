package com.example.virtualwallets.transactionComponent.model;

import android.util.Log;

import com.example.virtualwallets.AppBase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TransactionServiceImplement implements ITransactionService {

    @Override
    public List<DaoTransaction> getTransaction(String numberAccount) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppBase.BASE_URL_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build() ;

        List<DaoTransaction> transactionList = new LinkedList<>();
        SimpleDateFormat dateFormat  =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        try {
            String strDate = "2013-05-15T10:00:00-0700";
            Date dateNew = dateFormat.parse(strDate);

            transactionList.add(new DaoTransaction(dateNew, "Angel Beats", 230));
            transactionList.add(new DaoTransaction(dateNew, "Death Note", 456));
            transactionList.add(new DaoTransaction(dateNew, "Fate Stay Night", 342));
            transactionList.add(new DaoTransaction(dateNew, "Welcome to the NHK", 645));
            transactionList.add(new DaoTransaction(dateNew, "Suzumiya Haruhi", 459));
        } catch (ParseException ex) {
            Log.v("Exception", ex.getLocalizedMessage());
        }

        return transactionList;
    }
}

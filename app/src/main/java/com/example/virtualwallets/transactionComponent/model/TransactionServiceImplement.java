package com.example.virtualwallets.transactionComponent.model;

import android.util.Log;

import com.example.virtualwallets.AppBase;
import com.example.virtualwallets.utils.OnServiceResponse;
import com.example.virtualwallets.utils.WalletApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TransactionServiceImplement implements ITransactionService {

    private final String TAG = getClass().getSimpleName();

    @Override
    public void getTransaction(String numberAccount, int wallet_id, OnServiceResponse<List<DaoTransaction>>response) {

        String token = AppBase.retrieveset(AppBase.KEY_TOKEN);

        WalletApi api = AppBase.crearServicio(WalletApi.class,AppBase.BASE_URL_SERVICE);
        api.listTransactions(wallet_id,token)
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<TransactionResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //disposable.add(d);
                    }

                    @Override
                    public void onNext(List<TransactionResponse> list) {
                        if (list!=null){
                            List<DaoTransaction> transactionList = new LinkedList<>();
                            SimpleDateFormat dateFormat  =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

                            Collections.reverse(list);

                            for (TransactionResponse transac:list) {
                                try {
//                                    String strDate = "2013-05-15T10:00:00-0700";
                                    String strDate = transac.getDate();
                                    Date dateNew = dateFormat.parse(strDate);

                                    boolean debito = numberAccount.trim() == transac.getSourceWallet().getWalletNumber().trim();

                                    if (debito){
                                        transac.setTransactionAmount(transac.getTransactionAmount()*(-1));
                                        transac.setTransactionType("DEBITO:"+transac.getTransactionType());
                                    }

                                    transactionList.add(new DaoTransaction(dateNew,transac.getTransactionType(),transac.getTransactionAmount()));

                                } catch (ParseException ex) {
                                    Log.v("Exception", ex.getLocalizedMessage());
                                }
                            }
                            response.onComplet(transactionList);
                        }else {
                            response.onComplet(new LinkedList<>());
                            response.onError();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        response.onError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void getCurrentBalanceByNumberAccount(int wallet_id, OnServiceResponse<Double> response) {

        String token = AppBase.retrieveset(AppBase.KEY_TOKEN);

        WalletApi api = AppBase.crearServicio(WalletApi.class,AppBase.BASE_URL_SERVICE);
        api.listTransactions(wallet_id,token)
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<TransactionResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //disposable.add(d);
                    }

                    @Override
                    public void onNext(List<TransactionResponse> list) {
                        if (list!=null){
                            Collections.reverse(list);
                            response.onComplet(list.get(0).getSourceWallet().getBalance());
                        }else {
                            response.onComplet(0.0);
                            response.onError();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        response.onError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}

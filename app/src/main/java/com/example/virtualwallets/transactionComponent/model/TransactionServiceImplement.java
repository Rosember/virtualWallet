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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionServiceImplement implements ITransactionService {

    private final String TAG = getClass().getSimpleName();

    @Override
    public void getTransaction(String numberAccount, int wallet_id, OnServiceResponse<List<DaoTransaction>>servicio) {

        try {
            String token = AppBase.retrieveset(AppBase.KEY_TOKEN);

            WalletApi api = AppBase.crearServicio(WalletApi.class,AppBase.BASE_URL_SERVICE);
            Call<List<TransactionResponse>> variable = api.listTransactions(wallet_id,token);
            variable.enqueue(new Callback<List<TransactionResponse>>() {
                @Override
                public void onResponse(Call<List<TransactionResponse>> call, Response<List<TransactionResponse>> response) {
                    List<TransactionResponse> list= response.body();
                    if (list!=null){
                        List<DaoTransaction> transactionList = new LinkedList<>();
                        SimpleDateFormat dateFormat  =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

                        Collections.reverse(list);

                        for (TransactionResponse transac:list) {
                            try {
//                                    String strDate = "2013-05-15T10:00:00-0700";
                                String strDate = transac.getDate();
                                Date dateNew = dateFormat.parse(strDate);

                                boolean debito = numberAccount.trim().equals( transac.getSourceWallet().getWalletNumber().trim());
                                Log.d(TAG, "Numero de cuenta: "+ numberAccount.trim());
                                Log.d(TAG, "Numero de cuenta del Servicio: "+ transac.getSourceWallet().getWalletNumber().trim());
                                Log.d(TAG, "Numero de cuenta del Boolean: "+ debito);



                                if (debito){
                                    transac.setTransactionAmount(transac.getTransactionAmount()*(-1));
                                    transac.setTransactionType("DEBITO:"+transac.getTransactionType());
                                }

                                transactionList.add(new DaoTransaction(dateNew,transac.getTransactionType(),transac.getTransactionAmount()));

                            } catch (ParseException ex) {
                                Log.v("Exception", ex.getLocalizedMessage());
                            }
                        }
                        servicio.onComplet(transactionList);
                    }else {
                        servicio.onComplet(new LinkedList<>());
                        servicio.onError();
                    }
                }

                @Override
                public void onFailure(Call<List<TransactionResponse>> call, Throwable t) {
                    servicio.onError();
                }
            });


        }catch (Exception e){
            Log.d(TAG, e.getMessage());
        }



    }

    @Override
    public void getCurrentBalanceByNumberAccount(String numberAccount,int wallet_id, OnServiceResponse<Double> servicio) {


        try {
            String token = AppBase.retrieveset(AppBase.KEY_TOKEN);

            WalletApi api = AppBase.crearServicio(WalletApi.class,AppBase.BASE_URL_SERVICE);
            Call<List<TransactionResponse>> variable = api.listTransactions(wallet_id,token);
            variable.enqueue(new Callback<List<TransactionResponse>>() {
                @Override
                public void onResponse(Call<List<TransactionResponse>> call, Response<List<TransactionResponse>> response) {
                    List<TransactionResponse> list= response.body();
                    if (list!=null){
                        Collections.reverse(list);
                        if (list.get(0).getSourceWallet().getWalletNumber().trim().equals(numberAccount.trim())){
                            servicio.onComplet(list.get(0).getSourceWallet().getBalance());
                        }else{
                            servicio.onComplet(list.get(0).getDestinyWallet().getBalance());
                        }

                    }else {
                        servicio.onComplet(0.0);
                        servicio.onError();
                    }
                }

                @Override
                public void onFailure(Call<List<TransactionResponse>> call, Throwable t) {
                    servicio.onError();
                }
            });


        }catch (Exception e){
            Log.d(TAG, e.getMessage());
        }





    }
}

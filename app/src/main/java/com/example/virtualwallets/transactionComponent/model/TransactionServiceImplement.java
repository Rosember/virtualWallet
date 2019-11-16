package com.example.virtualwallets.transactionComponent.model;

import android.util.Log;

import com.example.virtualwallets.AppBase;
import com.example.virtualwallets.loginComponent.model.LoginResponse;
import com.example.virtualwallets.mainComponent.model.WalletsResponse;
import com.example.virtualwallets.transactionComponent.presenter.IBalancePresenter;
import com.example.virtualwallets.transactionComponent.presenter.ITransactionPresenter;
import com.example.virtualwallets.transactionComponent.presenter.TransactionPresenter;
import com.example.virtualwallets.transactionComponent.view.TransactionWalletsView;
import com.example.virtualwallets.transferComponent.model.Wallets;
import com.example.virtualwallets.utils.OnServiceResponse;
import com.example.virtualwallets.utils.WalletApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TransactionServiceImplement implements ITransactionService {

    private final String TAG = getClass().getSimpleName();

    @Override
    public void getTransaction(String numberAccount, OnServiceResponse<List<DaoTransaction>>response) {

        /*
        try {
            String token = AppBase.retrieveset(AppBase.KEY_TOKEN);
//            String userId = AppBase.retrieveset(AppBase.KEY_USER);
            String userId = "1";
            api = AppBase.crearServicio(WalletApi.class, AppBase.BASE_URL_SERVICE);
            api.listWallets(Integer.parseInt(userId), "Bearer " + token)
                    .subscribeOn(Schedulers.io())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<WalletsResponse>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(List<WalletsResponse> walletsResponses) {
                            List<Wallets> list = new ArrayList<>();
                            for (WalletsResponse w : walletsResponses){
                                list.add(new Wallets(w.getId(),w.getWalletNumber(),w.getBalance()));
                            }
                            serviceResponse.onComplet(list);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }catch (Exception ex){
            ex.printStackTrace();
        }
*/



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

        response.onComplet(transactionList);

    }

    @Override
    public void getCurrentBalanceByNumberAccount(String numberAccount, OnServiceResponse<Double> response) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppBase.BASE_URL_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build() ;

        response.onComplet(954.25);

    }
}

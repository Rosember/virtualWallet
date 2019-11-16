package com.example.virtualwallets.walletComponent.model;

import com.example.virtualwallets.AppBase;
import com.example.virtualwallets.transferComponent.model.Wallets;
import com.example.virtualwallets.utils.OnServiceResponse;
import com.example.virtualwallets.utils.WalletApi;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-12
 */
public class ListWalletService implements IListWalletService {

    private OnServiceResponse<List<Wallets>> serviceResponse;
    private Disposable disposable;
    WalletApi api;

    public ListWalletService(OnServiceResponse response) {
        this.serviceResponse = response;
        this.disposable = new CompositeDisposable();
    }

    @Override
    public void onLoadWallets() {
        try {
            String token = AppBase.retrieveset(AppBase.KEY_TOKEN);
            String userId = AppBase.retrieveset(AppBase.KEY_USER);
            api = AppBase.crearServicio(WalletApi.class, AppBase.BASE_URL_SERVICE);
            api.listWallets(Integer.parseInt(userId),  token)
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
    }

    @Override
    public void onDestroy() {
        disposable.dispose();
    }

}
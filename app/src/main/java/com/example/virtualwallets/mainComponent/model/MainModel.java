package com.example.virtualwallets.mainComponent.model;

import com.example.virtualwallets.AppBase;
import com.example.virtualwallets.POJOS.Wallets;
import com.example.virtualwallets.mainComponent.presenter.IMainPresenter;
import com.example.virtualwallets.utils.WalletApi;

import java.util.ArrayList;
import java.util.HashMap;
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
public class MainModel implements IMainModel{

    private IMainPresenter presenter;
    private Disposable disposable;
    WalletApi api;

    public MainModel(IMainPresenter presenter) {
        this.presenter = presenter;
        this.disposable = new CompositeDisposable();
    }

    @Override
    public void onLoadWallets() {
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
                            presenter.onLoadWalletsSuccess(list);
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
    public void logout() {
        String token = AppBase.retrieveset(AppBase.KEY_TOKEN);
        String userId = AppBase.retrieveset(AppBase.KEY_USER);
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id",userId);
        api = AppBase.crearServicio(WalletApi.class,AppBase.BASE_URL_SERVICE);
        api.logout("Bearer "+token,map)
        .observeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) presenter.onLogoutSucces();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    public void onResume() {
        disposable.dispose();
    }

}

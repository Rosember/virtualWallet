package com.example.virtualwallets.loginComponent.model;

import com.example.virtualwallets.AppBase;
import com.example.virtualwallets.utils.OnServiceResponse;
import com.example.virtualwallets.utils.WalletApi;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-15
 */
public class LogoutServiceImplement implements ILogoutService{

    private OnServiceResponse response;
    public CompositeDisposable disposable;
    WalletApi api;

    public LogoutServiceImplement(OnServiceResponse response) {
        this.response = response;
        this.disposable = new CompositeDisposable();

    }

    @Override
    public void onLogout() {
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
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) response.onComplet(true);
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

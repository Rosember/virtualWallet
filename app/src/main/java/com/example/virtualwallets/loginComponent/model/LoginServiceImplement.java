package com.example.virtualwallets.loginComponent.model;

import android.util.Log;

import com.example.virtualwallets.AppBase;
import com.example.virtualwallets.loginComponent.presenter.ILoginPresenter;
import com.example.virtualwallets.utils.WalletApi;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginServiceImplement implements ILoginPersistence {

    private final String TAG = getClass().getSimpleName();

    private ILoginPresenter presenter;

    public LoginServiceImplement(ILoginPresenter presenter) {

        this.presenter = presenter;
    }

    @Override
    public void validateCredentials(String user, String password) {
        try {
            WalletApi api = AppBase.crearServicio(WalletApi.class, AppBase.BASE_URL_SERVICE);
            HashMap<String, String> map = new HashMap<>();
            map.put("email", user);
            map.put("password", password);
            api.loginSession(map)
                    .subscribeOn(Schedulers.io())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<LoginResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(LoginResponse loginResponse) {
                            Log.d(TAG, "onResponse: " + loginResponse.getToken());

                            AppBase.saveset(AppBase.KEY_TOKEN, "Bearer "+loginResponse.getToken());
                            AppBase.saveset(AppBase.KEY_USER, ""+loginResponse.getUser().getId());
                            presenter.onLoginSucces();
                        }

                        @Override
                        public void onError(Throwable e) {
                            String mensaje = "Falla al conectar con el servicio";
                            Log.d(TAG, mensaje + ". Detalle del error:"+ e.getMessage());
                            presenter.onNetworkError();
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "validateCredentials: ");
            presenter.onLoginInvalidCredentials();
        }
    }
}

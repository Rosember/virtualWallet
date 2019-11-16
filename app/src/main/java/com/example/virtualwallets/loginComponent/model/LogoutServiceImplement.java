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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-15
 */
public class LogoutServiceImplement implements ILogoutService{

    private OnServiceResponse serviceResponse;
    public CompositeDisposable disposable;
    WalletApi api;

    public LogoutServiceImplement(OnServiceResponse response) {
        this.serviceResponse = response;
        this.disposable = new CompositeDisposable();

    }

    @Override
    public void onLogout() {
        String token = AppBase.retrieveset(AppBase.KEY_TOKEN);
        String userId = AppBase.retrieveset(AppBase.KEY_USER);
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id",userId);
        api = AppBase.crearServicio(WalletApi.class,AppBase.BASE_URL_SERVICE);

        Call<Boolean> callBack = api.logout(token,map);
        callBack.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean b = response.body();
                if (b!=null) serviceResponse.onComplet(true);
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                serviceResponse.onError();
            }
        });

    }
}

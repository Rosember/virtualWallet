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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
            Call<LoginResponse> callBack = api.loginSession(map);
            callBack.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse!= null) {
                        Log.d(TAG, "onResponse: " + loginResponse.getToken());

                        AppBase.saveset(AppBase.KEY_TOKEN, "Bearer " + loginResponse.getToken());
                        AppBase.saveset(AppBase.KEY_USER, "" + loginResponse.getUser().getId());
                        presenter.onLoginSucces();
                    }else
                        presenter.onLoginInvalidCredentials();
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    presenter.onNetworkError();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "validateCredentials: ");
            presenter.onLoginInvalidCredentials();
        }
    }
}

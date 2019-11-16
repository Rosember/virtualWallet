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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
            Call<List<WalletsResponse>> callback = api.listWallets(Integer.parseInt(userId),token);
            callback.enqueue(new Callback<List<WalletsResponse>>() {
                @Override
                public void onResponse(Call<List<WalletsResponse>> call, Response<List<WalletsResponse>> response) {
                    List<Wallets> list = new ArrayList<>();
                    List<WalletsResponse> walletsResponses = response.body();
                    if (walletsResponses != null) {
                        for (WalletsResponse w : walletsResponses) {
                            list.add(new Wallets(w.getId(), w.getWalletNumber(), w.getBalance()));
                        }
                        serviceResponse.onComplet(list);
                    }
                }

                @Override
                public void onFailure(Call<List<WalletsResponse>> call, Throwable t) {
                    serviceResponse.onError();
                }
            });
        }catch (Exception ex){
            ex.printStackTrace();
            serviceResponse.onError();
        }
    }

    @Override
    public void onDestroy() {
        disposable.dispose();
    }

}

package com.example.virtualwallets.transferComponent.model;


import com.example.virtualwallets.AppBase;
import com.example.virtualwallets.utils.OnServiceResponse;
import com.example.virtualwallets.utils.WalletApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-16
 */
public class CheckWallet implements ICheckWallet {

    @Override
    public void onDestroy() {

    }

    @Override
    public void findByNumberWallet(String number, OnServiceResponse<Integer> serviceResponse) {
        String token = AppBase.retrieveset(AppBase.KEY_TOKEN);
        WalletApi api = AppBase.crearServicio(WalletApi.class,AppBase.BASE_URL_SERVICE);
        Call<WalletResponse> call = api.findByNumberWallet(number,token);
        call.enqueue(new Callback<WalletResponse>() {
            @Override
            public void onResponse(Call<WalletResponse> call, Response<WalletResponse> response) {
                if (response.body()!=null){
                    int id = response.body().getId();
                    serviceResponse.onComplet(id);
                }else
                    serviceResponse.onError();
            }

            @Override
            public void onFailure(Call<WalletResponse> call, Throwable t) {
                serviceResponse.onError();
            }
        });
    }

}

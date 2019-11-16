package com.example.virtualwallets.transferComponent.model;

import android.util.Log;

import com.example.virtualwallets.AppBase;
import com.example.virtualwallets.utils.OnServiceResponse;
import com.example.virtualwallets.utils.WalletApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-13
 */
public class TransferService implements ITransferService {

    private final String TAG = getClass().getSimpleName();

    private OnServiceResponse<String> servicio;

    public TransferService(OnServiceResponse<String> servicio) {
        this.servicio = servicio;
    }

    @Override
    public void onTransfer(TransferRequest request) {
        try {
            String token = AppBase.retrieveset(AppBase.KEY_TOKEN);
            WalletApi api = AppBase.crearServicio(WalletApi.class, AppBase.BASE_URL_SERVICE);
            Call<TransferResponse> callback = api.transfer(token, request);
            callback.enqueue(new Callback<TransferResponse>() {
                @Override
                public void onResponse(Call<TransferResponse> call, Response<TransferResponse> response) {
                    Log.d(TAG, "onResponse: " + response.body());
                    if (response.body() != null)
                        servicio.onComplet("1");
                    else
                        servicio.onError();
                }

                @Override
                public void onFailure(Call<TransferResponse> call, Throwable t) {
                    t.printStackTrace();
                    servicio.onError();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            servicio.onError();
        }
    }

    @Override
    public void onDestroy() {

    }
}

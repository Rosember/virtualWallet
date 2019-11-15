package com.example.virtualwallets.transferComponent.model;

import com.example.virtualwallets.AppBase;
import com.example.virtualwallets.utils.OnServiceResponse;
import com.example.virtualwallets.utils.WalletApi;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-13
 */
public class TransferServiceImplement implements ITransferService {

    private OnServiceResponse servicio;

    public TransferServiceImplement(OnServiceResponse servicio) {
        this.servicio = servicio;
    }

    @Override
    public void onTransfer(TransferRequest request) {
        try {
            String token = AppBase.retrieveset(AppBase.KEY_TOKEN);
            WalletApi api = AppBase.crearServicio(WalletApi.class, AppBase.BASE_URL_SERVICE);
            Call<TransferResponse> callback = api.transfer(token,request);
            callback.enqueue(new Callback<TransferResponse>() {
                @Override
                public void onResponse(Call<TransferResponse> call, Response<TransferResponse> response) {
                    servicio.onComplet(true);
                }

                @Override
                public void onFailure(Call<TransferResponse> call, Throwable t) {
                    t.printStackTrace();
                    servicio.onError();
                }
            });
//            api.transfer(token, request)
//                    .subscribeOn(Schedulers.io())
//                    .subscribeOn(AndroidSchedulers.mainThread())
//                    .unsubscribeOn(Schedulers.io())
//                    .subscribe(new Observer<TransferResponse>() {
//                        @Override
//                        public void onSubscribe(Disposable d) {
//
//                        }
//
//                        @Override
//                        public void onNext(TransferResponse transferResponse) {
//                            servicio.onComplet(true);
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            e.printStackTrace();
//
//                        }
//
//                        @Override
//                        public void onComplete() {
//
//                        }
//                    });
        } catch (Exception e) {
            e.printStackTrace();
            servicio.onError();
        }
    }
}

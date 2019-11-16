package com.example.virtualwallets.utils;

import com.example.virtualwallets.loginComponent.model.LoginResponse;
import com.example.virtualwallets.walletComponent.model.WalletsResponse;
import com.example.virtualwallets.transferComponent.model.TransferRequest;
import com.example.virtualwallets.transferComponent.model.TransferResponse;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-13
 */
public interface WalletApi {

    @POST("auth/login")
    Observable<LoginResponse> loginSession(@Body HashMap<String,String> data);

    @POST("auth/logout")
    Observable<Boolean> logout(@Header("Authorization") String toke, @Body HashMap<String,String> data);

    @GET("users/{user}/wallets")
    Observable<List<WalletsResponse>> listWallets(@Path("user") int userId, @Header("Authorization") String token);

    @GET("wallets/{wallet_id}/transactions")
    Observable<List<WalletsResponse>> listTransactions(@Path("wallet_id") int wallet_id, @Header("Authorization") String token);

    @POST("transactions")
    Call<TransferResponse> transfer(@Header("Authorization") String token,@Body TransferRequest request);
}

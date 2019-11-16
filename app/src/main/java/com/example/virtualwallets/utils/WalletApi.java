package com.example.virtualwallets.utils;

import com.example.virtualwallets.loginComponent.model.LoginResponse;
import com.example.virtualwallets.transactionComponent.model.TransactionResponse;
import com.example.virtualwallets.transferComponent.model.TransferRequest;
import com.example.virtualwallets.transferComponent.model.TransferResponse;
import com.example.virtualwallets.transferComponent.model.WalletResponse;
import com.example.virtualwallets.walletComponent.model.WalletsResponse;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

//import com.example.virtualwallets.mainComponent.model.WalletsResponse;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-13
 */
public interface WalletApi {

    @POST("auth/login")
    Call<LoginResponse> loginSession(@Body HashMap<String, String> data);

    @POST("auth/logout")
    Call<Boolean> logout(@Header("Authorization") String toke, @Body HashMap<String, String> data);

    @GET("users/{user}/wallets")
    Call<List<WalletsResponse>> listWallets(@Path("user") int userId, @Header("Authorization") String token);


    @GET("wallets/{wallet_id}/transactions")
    Call<List<TransactionResponse>> listTransactions(@Path("wallet_id") int wallet_id, @Header("Authorization") String token);

    @POST("transactions")
    Call<TransferResponse> transfer(@Header("Authorization") String token, @Body TransferRequest request);

    @GET("wallets/by-number/{wallet_number_a}")
    Call<WalletResponse> findByNumberWallet(@Path("wallet_number_a") String number, @Header("Authorization") String token);
}

package com.example.virtualwallets.utils;

import com.example.virtualwallets.loginComponent.model.LoginResponse;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-13
 */
public interface WalletApi {

    @POST("auth/login")
    Observable<LoginResponse> loginSession(@Body HashMap<String,String> data);
}

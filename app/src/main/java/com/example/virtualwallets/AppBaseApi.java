package com.example.virtualwallets;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-14
 */
public class AppBaseApi {
    private static AppBaseApi _Instance;
    private static final String IP_1 = "10.1.3.102";
    private static final String IP_2 = "10.1.3.104";
    private static final String BASE_URL_SERVICE ="http://"+IP_2+":8081/api/";
    private Retrofit mRetrofit;

    private AppBaseApi(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static AppBaseApi getInstance() {
        if (_Instance != null){
            _Instance = new AppBaseApi();
        }
        return _Instance;
    }
}

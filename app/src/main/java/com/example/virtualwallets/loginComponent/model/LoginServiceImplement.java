package com.example.virtualwallets.loginComponent.model;

import com.example.virtualwallets.AppBase;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginServiceImplement implements ILoginService {



    @Override
    public boolean performLogin(String user, String password) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppBase.BASE_URL_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build() ;

        return true ;
    }
}

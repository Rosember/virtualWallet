package com.example.virtualwallets.loginComponent.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-13
 */
public class LoginResponse {
    @SerializedName("token")
    @Expose
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

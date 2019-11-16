package com.example.virtualwallets.walletComponent.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-14
 */
public class WalletsResponse {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("walletNumber")
    @Expose
    public String walletNumber;
    @SerializedName("balance")
    @Expose
    public Double balance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWalletNumber() {
        return walletNumber;
    }

    public void setWalletNumber(String walletNumber) {
        this.walletNumber = walletNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "WalletsResponse{" +
                "id=" + id +
                ", walletNumber='" + walletNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}

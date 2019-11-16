package com.example.virtualwallets.transferComponent.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-16
 */

public class WalletResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("walletNumber")
    @Expose
    private String walletNumber;
    @SerializedName("balance")
    @Expose
    private Double balance;
    @SerializedName("user")
    @Expose
    private TransferResponse.User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public TransferResponse.User getUser() {
        return user;
    }

    public void setUser(TransferResponse.User user) {
        this.user = user;
    }
}
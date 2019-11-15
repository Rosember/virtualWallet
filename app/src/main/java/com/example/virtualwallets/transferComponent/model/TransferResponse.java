package com.example.virtualwallets.transferComponent.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-15
 */

public class TransferResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("responsiblePersonName")
    @Expose
    private String responsiblePersonName;
    @SerializedName("transactionAmount")
    @Expose
    private Double transactionAmount;
    @SerializedName("transactionType")
    @Expose
    private String transactionType;
    @SerializedName("sourceWallet")
    @Expose
    private SourceWallet sourceWallet;
    @SerializedName("destinyWallet")
    @Expose
    private DestinyWallet destinyWallet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getResponsiblePersonName() {
        return responsiblePersonName;
    }

    public void setResponsiblePersonName(String responsiblePersonName) {
        this.responsiblePersonName = responsiblePersonName;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public SourceWallet getSourceWallet() {
        return sourceWallet;
    }

    public void setSourceWallet(SourceWallet sourceWallet) {
        this.sourceWallet = sourceWallet;
    }

    public DestinyWallet getDestinyWallet() {
        return destinyWallet;
    }

    public void setDestinyWallet(DestinyWallet destinyWallet) {
        this.destinyWallet = destinyWallet;
    }


    public class User {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

    }

    public class SourceWallet {

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
        private User user;

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

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

    }


    public class DestinyWallet {

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
        private User user;

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

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

    }


}

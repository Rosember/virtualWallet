package com.example.virtualwallets.transferComponent.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-14
 */
public class TransferRequest {

    @SerializedName("responsible_person_name")
    @Expose
    private String responsiblePersonName;
    @SerializedName("transaction_amount")
    @Expose
    private Double transactionAmount;
    @SerializedName("transaction_type")
    @Expose
    private String transactionType;
    @SerializedName("source_wallet_id")
    @Expose
    private Integer sourceWalletId;
    @SerializedName("destiny_wallet_id")
    @Expose
    private Integer destinyWalletId;

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

    public Integer getSourceWalletId() {
        return sourceWalletId;
    }

    public void setSourceWalletId(Integer sourceWalletId) {
        this.sourceWalletId = sourceWalletId;
    }

    public Integer getDestinyWalletId() {
        return destinyWalletId;
    }

    public void setDestinyWalletId(Integer destinyWalletId) {
        this.destinyWalletId = destinyWalletId;
    }

    @Override
    public String toString() {
        return "TransferRequest{" +
                "responsiblePersonName='" + responsiblePersonName + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", transactionType='" + transactionType + '\'' +
                ", sourceWalletId=" + sourceWalletId +
                ", destinyWalletId=" + destinyWalletId +
                '}';
    }
}
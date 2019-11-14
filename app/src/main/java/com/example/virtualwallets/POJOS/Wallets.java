package com.example.virtualwallets.POJOS;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-11
 */
public class Wallets implements Parcelable {

    private int id;
    private String nombre;
    private Double saldo;

    public Wallets(int id, String nombre, Double saldo) {
        this.id = id;
        this.nombre = nombre;
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Wallets{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", saldo=" + saldo +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombre);
        dest.writeDouble(saldo);
    }
}

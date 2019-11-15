package com.example.virtualwallets.utils;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-15
 */
public interface OnServiceResponse<T> {

    void onComplet(T result);
    void onError();
}

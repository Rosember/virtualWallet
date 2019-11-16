package com.example.virtualwallets.transferComponent.model;

import com.example.virtualwallets.utils.OnServiceResponse;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-16
 */
public interface ICheckWallet {
    void onDestroy();
    void findByNumberWallet(String number, OnServiceResponse<Integer> serviceResponse);
}

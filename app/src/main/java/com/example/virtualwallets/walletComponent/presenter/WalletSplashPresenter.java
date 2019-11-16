package com.example.virtualwallets.walletComponent.presenter;

import android.util.Log;

import com.example.virtualwallets.walletComponent.IWalletSplashView;
import com.example.virtualwallets.utils.CheckSession;
import com.example.virtualwallets.utils.OnTaskCompleted;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-12
 */
public class WalletSplashPresenter implements OnTaskCompleted, IWalletSplashPresenter {

    private final String TAG = getClass().getSimpleName();
    private IWalletSplashView view;

    public WalletSplashPresenter(IWalletSplashView view) {
        this.view = view;
    }

    @Override
    public void checkSession() {
        Log.d(TAG, "checkSession: inicio ");
        new CheckSession(this).execute();
    }

    @Override
    public void onTaskComplete(boolean result) {
        Log.d(TAG, "onTaskComplete: resultado "+result);
        if (result){
            view.goToMainView();
        }else{
            view.goToLoginView();
        }
    }
}

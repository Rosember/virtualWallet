package com.example.virtualwallets.mainComponent;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.virtualwallets.R;
import com.example.virtualwallets.loginComponent.view.LoginActivity;
import com.example.virtualwallets.mainComponent.presenter.IWalletSplashPresenter;
import com.example.virtualwallets.mainComponent.presenter.WalletSplashPresenter;
import com.example.virtualwallets.mainComponent.view.MainActivity;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-12
 */
public class WalletSplashActivity extends AppCompatActivity implements IWalletSplashView {

    private IWalletSplashPresenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_splash);
        presenter = new WalletSplashPresenter(this);
        presenter.checkSession();
    }

    @Override
    public void goToLoginView() {
        Intent i = new Intent(WalletSplashActivity.this, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);

        finish();
    }

    @Override
    public void goToMainView() {
        Intent i = new Intent(WalletSplashActivity.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);

        finish();
    }
}

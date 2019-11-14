package com.example.virtualwallets.loginComponent.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.virtualwallets.AppBase;
import com.example.virtualwallets.mainComponent.view.MainActivity;
import com.example.virtualwallets.R;
import com.example.virtualwallets.loginComponent.model.Login;
import com.example.virtualwallets.loginComponent.model.LoginServiceImplement;
import com.example.virtualwallets.loginComponent.presenter.LoginPresenter;
import com.example.virtualwallets.utils.CheckSession;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import com.example.virtualwallets.transactionComponent.view.TransactionWalletsView;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    private LoginPresenter loginPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);

        loginPresenter = new LoginPresenter(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loadingProgressBar.setVisibility(View.VISIBLE);
                loginPresenter.onLoginButtonPressed(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });
    }

    @Override
    public void showLoginSuccessMessage() {
        String welcome = getString(R.string.welcome);
//        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
//        Intent i = new Intent(LoginActivity.this, TransactionWalletsView.class);
//        startActivity(i);
//        this.finish();
        saveSession();
    }

    @Override
    public void showInvalidCredentialsMessage() {
        String invalidCredential = getString(R.string.login_failed);
//        Toast.makeText(getBaseContext(), invalidCredential, Toast.LENGTH_LONG).show();
        Log.d("LoginActivity", "showInvalidCredentialsMessage: error");
    }

    @Override
    public void showNetworkErrorMessage() {
        String networkError = getString(R.string.network_error);
//        Toast.makeText(getApplicationContext(), networkError, Toast.LENGTH_LONG).show();
    }

    @Override
    public void saveSession() {
        Completable.fromAction(()->{
            SimpleDateFormat format1 = new SimpleDateFormat(CheckSession.YYYY_MM_DD_T_HH_MM_SS_SSS, Locale.US);
            String sessionDate = format1.format(Calendar.getInstance().getTime());
            AppBase.getInstance().saveset(CheckSession.KEY_SESSION, sessionDate);
        }).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver(){

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}

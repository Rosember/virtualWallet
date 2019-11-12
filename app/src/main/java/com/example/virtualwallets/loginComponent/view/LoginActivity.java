package com.example.virtualwallets.loginComponent.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.virtualwallets.mainComponent.view.MainView;
import com.example.virtualwallets.R;
import com.example.virtualwallets.loginComponent.model.Login;
import com.example.virtualwallets.loginComponent.model.LoginServiceImplement;
import com.example.virtualwallets.loginComponent.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    private LoginPresenter loginPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);

        loginPresenter = new LoginPresenter(this, new Login(new LoginServiceImplement()));

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
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        Intent i = new Intent(LoginActivity.this, MainView.class);

    }

    @Override
    public void showInvalidCredentialsMessage() {
        String invalidCredential = getString(R.string.login_failed);
        Toast.makeText(getApplicationContext(), invalidCredential, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showNetworkErrorMessage() {
        String networkError = getString(R.string.network_error);
        Toast.makeText(getApplicationContext(), networkError, Toast.LENGTH_LONG).show();
    }
}

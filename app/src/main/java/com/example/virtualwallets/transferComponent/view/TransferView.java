package com.example.virtualwallets.transferComponent.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.virtualwallets.POJOS.Wallets;
import com.example.virtualwallets.R;
import com.example.virtualwallets.transferComponent.presenter.ITransferPresenter;
import com.example.virtualwallets.transferComponent.presenter.TranseferPresenter;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-11
 */
public class TransferView extends AppCompatActivity implements ITransferView {

    private final String TAG = getClass().getSimpleName();
    private ITransferPresenter presenter;
    @BindView(R.id.toolbar_transfer)
    public Toolbar toolbar;

    @BindView(R.id.rl_container_transfer)
    public RelativeLayout contenedor;

    @BindView(R.id.edit_text_cuenta_origen)
    public EditText origen;
    @BindView(R.id.edit_text_list_destino)
    public EditText listDestinos;
    @BindView(R.id.edit_text_nro_cuenta)
    public EditText nroCuenta;
    @BindView(R.id.edit_text_monto)
    public EditText monto;

    @BindView(R.id.til_cuenta_origen)
    public TextInputLayout tilOrigen;
    @BindView(R.id.til_list_destino)
    public TextInputLayout tilListDestino;
    @BindView(R.id.til_nro_cuenta)
    public TextInputLayout tilNroCuenta;
    @BindView(R.id.til_monto)
    public TextInputLayout tilMonto;
    List<Wallets> listWallet;
    private CharSequence[] destinos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        destinos = new String[]{"Propios", "Otros"};

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Transferencia");
        toolbar.setNavigationOnClickListener(arrow -> onBackPressed());
        presenter = new TranseferPresenter(this);

    }


    @OnClick({R.id.rb_otros, R.id.rb_propios})
    void onGenderSelected(RadioButton radioButton) {
        switch (radioButton.getId()) {
            case R.id.rb_propios:
                Log.d(TAG, "onGenderSelected: propios");
                tilNroCuenta.setVisibility(View.GONE);
                nroCuenta.setText(null);
                tilListDestino.setVisibility(View.VISIBLE);
                break;
            case R.id.rb_otros:
                tilListDestino.setVisibility(View.GONE);
                listDestinos.setText(null);
                tilNroCuenta.setVisibility(View.VISIBLE);
                Log.d(TAG, "onGenderSelected: otros");
                break;
        }
    }

    @OnClick(R.id.btn_cancel_transfer)
    public void onClickCancel(View v) {

    }

    @OnClick(R.id.btn_send_transfer)
    public void onClickSned(View v) {
        Snackbar.make(contenedor, "Enviando", Snackbar.LENGTH_SHORT).show();
    }

    private void showCustomDialog(final String titulo, final CharSequence[] list) {
        AlertDialog alertDialog = new AlertDialog.Builder(TransferView.this)
                .setTitle(titulo)
                .setItems(list, (dialog, which) -> selectionDialog(titulo, which)).create();
        alertDialog.show();
    }

    private void selectionDialog(String type, int pos) {
        switch (type) {
            case "Destino":
//                destino.setText(destinos[pos]);
                if (pos == 0) {

                } else {

                }
                break;
        }
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void onsuccess() {

    }
}

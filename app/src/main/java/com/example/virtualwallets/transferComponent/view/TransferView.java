package com.example.virtualwallets.transferComponent.view;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.virtualwallets.R;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-11
 */
public class TransferView extends AppCompatActivity implements ITransferView {

    @BindView(R.id.toolbar_transfer)
    public Toolbar toolbar;

    @BindView(R.id.edit_text_cuenta_origen)
    public EditText origen;
    @BindView(R.id.edit_text_destino)
    public EditText destino;
    @BindView(R.id.edit_text_list_destino)
    public EditText listDestinos;
    @BindView(R.id.edit_text_nro_cuenta)
    public EditText nroCuenta;
    @BindView(R.id.edit_text_monto)
    public EditText monto;

    @BindView(R.id.til_cuenta_origen)
    public TextInputLayout tilOrigen;
    @BindView(R.id.til_destino)
    public TextInputLayout tilDestino;
    @BindView(R.id.til_list_destino)
    public TextInputLayout tilListDestion;
    @BindView(R.id.til_nro_cuenta)
    public TextInputLayout tilNroCuenta;
    @BindView(R.id.til_monto)
    public TextInputLayout tilMonto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("Transferencia");
        toolbar.setNavigationOnClickListener(arrow -> onBackPressed());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @OnClick(R.id.btn_cancel_transfer)
    public void onClickCancel(View v) {

    }

    @OnClick(R.id.btn_send_transfer)
    public void onClickSned(View v) {

    }
}

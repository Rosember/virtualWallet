package com.example.virtualwallets.transferComponent.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.virtualwallets.R;
import com.example.virtualwallets.transferComponent.model.TransferRequest;
import com.example.virtualwallets.transferComponent.model.Wallets;
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

    public static final String MIS_CUENTAS = "Billetera";
    public static final String MIS_BILLETERA = "Mis Billeteras";
    private static final String TRANSFER_TYPE = "TRANSFERENCE";
    private final String TAG = getClass().getSimpleName();
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
    @BindView(R.id.edit_text_responsable)
    public EditText responsable;
    @BindView(R.id.edit_text_monto)
    public EditText monto;
    @BindView(R.id.til_cuenta_origen)
    public TextInputLayout tilOrigen;
    @BindView(R.id.til_list_destino)
    public TextInputLayout tilListDestino;
    @BindView(R.id.til_nro_cuenta)
    public TextInputLayout tilNroCuenta;
    @BindView(R.id.til_responsable)
    public TextInputLayout tilResponsable;
    @BindView(R.id.til_monto)
    public TextInputLayout tilMonto;
    @BindView(R.id.rb_propios)
    public RadioButton rbPropios;
    @BindView(R.id.rb_otros)
    public RadioButton rbOtros;
    private ITransferPresenter presenter;
    private TransferRequest request;
    private List<Wallets> listWallet;
    private CharSequence[] listOriegen;
    private CharSequence[] listMisDestinos;

    private int origenSelectionPosition;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        request = new TransferRequest();

        Log.d(TAG, "onCreate: object "+request.toString());
        listWallet = new ArrayList<>();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Transferencia");
        toolbar.setNavigationOnClickListener(arrow -> onBackPressed());
        presenter = new TranseferPresenter(this);
        presenter.onLoadWalletCombo();
        nroCuenta.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                String value = String.valueOf(nroCuenta.getText());
                Log.d(TAG, "onCreate: ...... -> " + value);
                // search for request api with nroWallet
                nroCuenta.setEnabled(false);
            }
        });

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

    private void loadComboMisCuentas() {
        listMisDestinos = new CharSequence[listWallet.size() - 1];
        int j = 0;
        for (int i = 0; i < listWallet.size(); i++) {
            if (i != origenSelectionPosition) {
                listMisDestinos[j] = listWallet.get(i).getNombre();
                j++;
            }
        }

    }

    @OnClick(R.id.edit_text_cuenta_origen)
    public void onClickOrigin(View v) {
        Log.d(TAG, "onClickOrigin: ");
        showCustomDialog(MIS_CUENTAS, listOriegen);
    }

    @OnClick(R.id.edit_text_list_destino)
    public void onclickDestinosList(View v) {
        Log.d(TAG, "onclickDestinosList: ");
        showCustomDialog(MIS_BILLETERA, listMisDestinos);
    }

    @OnClick(R.id.btn_cancel_transfer)
    public void onClickCancel(View v) {

    }

    @OnClick(R.id.btn_send_transfer)
    public void onClickSned(View v) {
        Snackbar.make(contenedor, "Enviando", Snackbar.LENGTH_SHORT).show();
        request.setTransactionType(TRANSFER_TYPE);
        if (validateTransfer()) {
            Log.d(TAG, "onClickSned: enviando "+request.toString());
            presenter.sendTransfer(request);
        }else{
            Log.d(TAG, "onClickSned: erro validar");
        }
    }

    private boolean validateTransfer() {
        String nombre = responsable.getText().toString();
        String strOrigen = origen.getText().toString().trim();
        if (strOrigen.equals("")) {
            tilListDestino.setError("Debe seleccionar este campo");
            return false;
        }

        if (!rbPropios.isChecked() && !rbOtros.isChecked()) {
            showAlertDialog("Error", "Debe seleccionar Destino", -1);
        }

        if (rbPropios.isChecked() && request.getDestinyWalletId() == null) {
            tilListDestino.setError("Debe seleccionar destinatario");
            return false;

        }
        String cuenta = nroCuenta.getText().toString();
        if (rbOtros.isChecked() && cuenta.equals("")) {
            tilNroCuenta.setError("Debe ingresar nro de cuenta");
            return false;

        }
        if (nombre.equals("")) {
            tilResponsable.setError("Debe ingresar nombre");
            return false;

        }
        request.setResponsiblePersonName(nombre);
        String strMonto = monto.getText().toString().trim();
        if (strMonto.equals("")) {
            tilMonto.setError("Debe ingresar un monto");
            return false;
        }

        Double m = Double.parseDouble(strMonto);
        request.setTransactionAmount(m);
        return true;
    }

    private void showAlertDialog(final String titulo, final String text, int iconId) {
        AlertDialog alertDialog = new AlertDialog.Builder(TransferView.this).create();

        alertDialog.setTitle(titulo);
        alertDialog.setMessage(text);
        if (iconId > 0) alertDialog.setIcon(iconId);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", (DialogInterface d, int which) -> {
            d.dismiss();
            if (iconId==R.drawable.ic_check_circle_green_800_48dp){
                onBackPressed();
            }
        });
        alertDialog.show();
    }

    private void showCustomDialog(final String titulo, final CharSequence[] list) {
        AlertDialog alertDialog = new AlertDialog.Builder(TransferView.this)
                .setTitle(titulo)
                .setItems(list, (dialog, which) -> selectionDialog(titulo, which)).create();
        alertDialog.show();
    }

    private void selectionDialog(String type, int pos) {
        switch (type) {
            case MIS_CUENTAS:
                request.setSourceWalletId(listWallet.get(pos).getId());
                Log.d(TAG, "selectionDialog: position = " + pos);
                origenSelectionPosition = pos;
                origen.setText(listOriegen[pos]);
                listDestinos.setText("");
                request.setDestinyWalletId(null);
                loadComboMisCuentas();
                break;
            case MIS_BILLETERA:
                request.setDestinyWalletId(searchDestinoId(listMisDestinos[pos].toString()));
                listDestinos.setText(listMisDestinos[pos]);
                break;
        }
    }

    private int searchDestinoId(String nroCuenta) {
        for (int i = 0; i < listWallet.size(); i++) {
            if (listWallet.get(i).getNombre().equals(nroCuenta)) {
                return listWallet.get(i).getId();
            }
        }
        return -1;
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void onSuccess() {
        showAlertDialog("Aceptado", "Se realiazo la transferencia", R.drawable.ic_check_circle_green_800_48dp);
    }

    @Override
    public void onErrorLoadWallet() {
        tilOrigen.setError("Error loading wallets");
    }

    @Override
    public void onErrorTransfer() {
        showAlertDialog("Rechazado","Ocurrio un problema en la transaccion",R.drawable.ic_warning_yellow_700_48dp);
    }

    @Override
    public void onErrorCheckWallet() {

    }

    @Override
    public void findByNumberWallet(String number) {

    }

    @Override
    public void onLoadListWallet(List<Wallets> walletsList) {
        Log.d(TAG, "onLoadListWallet: resul");
        if (listWallet.size() > 0) listWallet.clear();
        listWallet.addAll(walletsList);

        listOriegen = new CharSequence[walletsList.size()];
        for (int i = 0; i < walletsList.size(); i++) {
            listOriegen[i] = walletsList.get(i).getNombre();
        }
        origen.setEnabled(true);
    }
}

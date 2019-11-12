package com.example.virtualwallets.transactionComponent.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.virtualwallets.R;
import com.example.virtualwallets.transactionComponent.model.DaoTransaction;
import com.example.virtualwallets.transactionComponent.presenter.TransactionPresenter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-11
 */
public class TransactionWalletsView extends AppCompatActivity implements ITransactionWalletsView{

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    private String numberAccount;
    private float balance;

    private TextView textView_NumberAccount;
    private TextView textView_balance;

    private TransactionPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        numberAccount = "2421560000562";
        balance = 352;

        presenter = new TransactionPresenter(this);
        presenter.onGetAllTransactions(numberAccount);
    }

    @Override
    public void showAccount() {
        textView_NumberAccount =  findViewById(R.id.tv_nro_cuenta_transacciones) ;
        textView_NumberAccount.setText("Nº Cuenta:  "+  numberAccount);

        textView_balance =  findViewById(R.id.tv_saldo_transacciones);
        textView_balance.setText("Saldo : " + String.valueOf(balance) + " $");
    }

    @Override
    public void showTransactions(List<DaoTransaction> transactionList) {
        // Inicializar Animes
        List items = new ArrayList();

        for (DaoTransaction transaction : transactionList){
            items.add(transaction);
        }

        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.rv_listado_transacciones);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new TransactionAdapter(items);
        recycler.setAdapter(adapter);
    }

    @Override
    public void showNoTransacton() {
        String networkError = getString(R.string.there_are_no_transaction);
        Toast.makeText(getApplicationContext(), networkError, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showNetworkErrorMessage() {
        String networkError = getString(R.string.network_error);
        Toast.makeText(getApplicationContext(), networkError, Toast.LENGTH_LONG).show();
    }
}

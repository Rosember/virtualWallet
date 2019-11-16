package com.example.virtualwallets.transactionComponent.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.virtualwallets.R;
import com.example.virtualwallets.transactionComponent.model.DaoTransaction;
import com.example.virtualwallets.transactionComponent.presenter.TransactionPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TransactionWalletsView extends AppCompatActivity implements ITransactionWalletsView{

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    private SwipeRefreshLayout swipeRefreshLayout;

    private String numberAccount;
    private int walletID;

    @BindView(R.id.toolbar_transaction)
    public Toolbar toolbar;

    private TextView textView_NumberAccount;
    private TextView textView_balance;

    private TransactionPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.title_transaction);
        }
        toolbar.setNavigationOnClickListener(arrow-> onBackPressed());

        String numeroCuenta = getIntent().getExtras().getString("numeroCuenta");
        int idBilletera = getIntent().getExtras().getInt("idCuenta");
        numberAccount = numeroCuenta;
        walletID = idBilletera;

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);

        presenter = new TransactionPresenter(this);
        presenter.getCurrentBalance(numberAccount, walletID);
        presenter.getTransactions(numberAccount,walletID);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Esto se ejecuta cada vez que se realiza el gesto
                swipeRefreshLayout.setRefreshing(true);
                presenter.getCurrentBalance(numberAccount, walletID);
                presenter.getTransactions(numberAccount,walletID);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                //finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void showAccount() {
        textView_NumberAccount =  findViewById(R.id.tv_nro_cuenta_transacciones) ;
        textView_NumberAccount.setText("Nº Cuenta:  "+  numberAccount);
    }

    @Override
    public void showCurrentBalance(double balance) {
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
    public void showNoTransaction() {
        String networkError = getString(R.string.there_are_no_transaction);
        Toast.makeText(getApplicationContext(), networkError, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showNetworkErrorMessage() {
        String networkError = getString(R.string.network_error);
        Toast.makeText(getApplicationContext(), networkError, Toast.LENGTH_LONG).show();
    }
}

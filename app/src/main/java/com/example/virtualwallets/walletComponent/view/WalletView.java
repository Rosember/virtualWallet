package com.example.virtualwallets.walletComponent.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.virtualwallets.AppBase;
import com.example.virtualwallets.transferComponent.model.Wallets;
import com.example.virtualwallets.R;
import com.example.virtualwallets.loginComponent.view.LoginActivity;
import com.example.virtualwallets.walletComponent.presenter.IWalletPresenter;
import com.example.virtualwallets.walletComponent.presenter.WalletPresenter;
import com.example.virtualwallets.transactionComponent.view.TransactionWalletsView;
import com.example.virtualwallets.transferComponent.view.TransferView;
import com.example.virtualwallets.utils.OnItemRecyclerViewClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-11
 */
public class WalletView extends AppCompatActivity implements IWalletView, OnItemRecyclerViewClickListener {


    private final String TAG = getClass().getSimpleName();
    @BindView(R.id.rv_list_wallet_main)
    public RecyclerView recyclerView;
    @BindView(R.id.fv_main_action_transaction)
    public FloatingActionButton actionButton;
    @BindView(R.id.main_container)
    public RelativeLayout container;
    @BindView(R.id.toolbar_main)
    public Toolbar toolbar;
    @BindView(R.id.swipeRefresh_list_main)
    public SwipeRefreshLayout refreshLayout;

    private AdapterMainRecyclerView adapter;
    private IWalletPresenter presenter;
    private List<Wallets> listWallet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new WalletPresenter(this);
        setupRecyclerView();
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("Wallet");


    }

    private void setupRecyclerView() {
        listWallet = new ArrayList<>();
        adapter = new AdapterMainRecyclerView(listWallet, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getParent()));
        recyclerView.setAdapter(adapter);
        refreshLayout.setColorSchemeResources(R.color.md_indigo_500, R.color.md_red_500, R.color.md_orange_500);
        refreshLayout.setOnRefreshListener(() -> onLoad());
        onLoad();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_main_logout:
                logout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fv_main_action_transaction)
    public void onClickFvButton(View view) {
        Snackbar.make(container, "New Transaction", Snackbar.LENGTH_SHORT).show();
        Intent i = new Intent(this, TransferView.class);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public void itemClick(Object o, int pos) {
        Log.d(TAG, "itemClick: mainActivity ");
        Intent i = new Intent(WalletView.this, TransactionWalletsView.class);
        Wallets wallets = (Wallets) o;
        i.putExtra("numeroCuenta", wallets.getNombre());
        i.putExtra("idCuenta", wallets.getId());

        startActivity(i);
    }

    @Override
    public void logout() {
        presenter.onLogout();
    }

    @Override
    public void logoutSuccess() {
        AppBase.clearAllSharedPreference();
        Intent i = new Intent(WalletView.this, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    @Override
    public void onLoad() {
        onStartRefreshSwipeWallet();
        presenter.onLoadWallets();
    }

    @Override
    public void onLoadSuccess(List<Wallets> wallets) {
        Log.d(TAG, "onLoadSuccess: "+wallets.toString());
        if (wallets.size()>0){
            Log.d(TAG, "onLoadSuccess: tamano "+wallets.size());
        }
        if (this.listWallet.size()>0){
            this.listWallet.clear();
        }
        this.listWallet.addAll(wallets);
        onStopRefreshSwipeWallet();
        this.adapter.notifyDataSetChanged();

    }

    @Override
    public void onStopRefreshSwipeWallet() {
        refreshLayout.setRefreshing(false);

    }

    @Override
    public void onStartRefreshSwipeWallet() {
        if (!refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(true);
        }
    }
}

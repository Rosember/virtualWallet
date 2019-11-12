package com.example.virtualwallets.mainComponent.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.virtualwallets.AppBase;
import com.example.virtualwallets.POJOS.Wallets;
import com.example.virtualwallets.R;
import com.example.virtualwallets.mainComponent.presenter.IMainPresenter;
import com.example.virtualwallets.mainComponent.presenter.MainPresenter;
import com.example.virtualwallets.transferComponent.view.TransferView;
import com.example.virtualwallets.utils.CheckSession;
import com.example.virtualwallets.utils.OnItemRecyclerViewClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-11
 */
public class MainActivity extends AppCompatActivity implements IMainView, OnItemRecyclerViewClickListener {


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
    private IMainPresenter presenter;
    private List<Wallets> listWallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenter(this);
        setupRecyclerView();
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("Wallet");
    }

    private void setupRecyclerView() {
        listWallet = new ArrayList<>();
        listWallet.add(new Wallets());
        adapter = new AdapterMainRecyclerView(listWallet, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getParent()));
        recyclerView.setAdapter(adapter);
        refreshLayout.setColorSchemeResources(R.color.md_indigo_500,R.color.md_red_500,R.color.md_orange_500);
        refreshLayout.setOnRefreshListener(() -> onLoad());
        onLoad();
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
        presenter.onResume();
    }

    @Override
    public void itemClick(Object o, int pos) {

    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onStopRefreshSwipeWallet() {
        adapter.notifyDataSetChanged();
        refreshLayout.setRefreshing(false);

    }

    @Override
    public void onStartRefreshSwipeWallet() {
        if(!refreshLayout.isRefreshing()){
            refreshLayout.setRefreshing(true);
        }
    }
}

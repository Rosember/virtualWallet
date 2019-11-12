package com.example.virtualwallets.mainComponent.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.virtualwallets.POJOS.Wallets;
import com.example.virtualwallets.R;
import com.example.virtualwallets.mainComponent.presenter.IMainPresenter;
import com.example.virtualwallets.mainComponent.presenter.MainPresenter;
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
public class MainView extends AppCompatActivity implements OnItemRecyclerViewClickListener {


    private AdapterMainRecyclerView adapter;

    @BindView(R.id.rv_listado_transacciones)
    public RecyclerView recyclerView;
    @BindView(R.id.fv_main_action_transaction)
    public FloatingActionButton actionButton;

    @BindView(R.id.main_container)
    public LinearLayout container;

    private IMainPresenter presenter;
    private List<Wallets> listWallet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenter();
        setupRecyclerView();
    }

    private void setupRecyclerView(){
        listWallet = new ArrayList<>();
        adapter = new AdapterMainRecyclerView(listWallet,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getParent()));
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.fv_main_action_transaction)
    public void onClickFvButton(View view){
        Snackbar.make(container,"New Transaction",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void itemClick(Object o, int pos) {

    }
}

package com.example.virtualwallets.mainComponent.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.virtualwallets.POJOS.Wallets;
import com.example.virtualwallets.R;
import com.example.virtualwallets.utils.OnItemRecyclerViewClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @autor Ing. Carlos G. Cruz Andia
 * Creado el 2019-11-11
 */
public class AdapterMainRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    OnItemRecyclerViewClickListener listener;
    List<Wallets> list;
    public AdapterMainRecyclerView(List<Wallets> walletsList, OnItemRecyclerViewClickListener listener){
        this.listener = listener;
        this.list = walletsList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewMain(LayoutInflater.from(parent.getContext()),parent,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((RecyclerViewMain) holder).bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecyclerViewMain extends RecyclerView.ViewHolder implements View.OnClickListener{

        OnItemRecyclerViewClickListener listener;

        @BindView(R.id.tv_item_nro_cuenta_billetera)
        public TextView walletId;

        @BindView(R.id.tv_item_saldo_billetera)
        public TextView walletSaldo;

        private  Wallets wallets;

        public RecyclerViewMain(LayoutInflater inflater, ViewGroup parent,OnItemRecyclerViewClickListener listener) {
            super(inflater.inflate(R.layout.item_wallet, parent, false));
            ButterKnife.bind(this, itemView);
        }

        public void bind(Wallets wallets){
            this.wallets = wallets;
        }

        @Override
        public void onClick(View v) {
            listener.itemClick(this.wallets,getPosition());
        }
    }
}
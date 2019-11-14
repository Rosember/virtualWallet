package com.example.virtualwallets.transactionComponent.view;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.virtualwallets.R;
import com.example.virtualwallets.transactionComponent.model.DaoTransaction;

import java.sql.Date;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {


    private List<DaoTransaction> items;

    public static class TransactionViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView fecha;
        public TextView glosa;
        public TextView monto;

        public TransactionViewHolder(View v) {
            super(v);
            fecha = (TextView) v.findViewById(R.id.tv_item_transaction_fecha);
            glosa = (TextView) v.findViewById(R.id.tv_item_transaction_nombre);
            monto = (TextView) v.findViewById(R.id.tv_item_transaction_saldo);
        }
    }

    public TransactionAdapter(List<DaoTransaction> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_transaction, viewGroup, false);
        return new TransactionViewHolder(v);
    }


    @Override
    public void onBindViewHolder(TransactionViewHolder viewHolder, int i) {
        viewHolder.fecha.setText(String.valueOf(items.get(i).getFecha()));
        viewHolder.glosa.setText(items.get(i).getGlosa());
        viewHolder.monto.setText("$:"+ String.valueOf(items.get(i).getMonto()));
        if (items.get(i).getMonto()<0){
            viewHolder.monto.setTextColor(Color.RED);
        }
    }
}

package com.example.gasapp.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gasapp.R;
import com.example.gasapp.model.PedidoTotal;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterPedidos extends RecyclerView.Adapter<MyAdapterPedidos.MyViewHolder> {
        List<PedidoTotal> listaPedidosTotais = new ArrayList<>();
        Context context;
        public MyAdapterPedidos(Context context, List<PedidoTotal> listaPedidosTotais) {
            this.context = context;
            this.listaPedidosTotais = listaPedidosTotais;
        }


    @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View itemList = LayoutInflater.from(viewGroup.getContext()).
                    inflate(R.layout.adapter_card_icones_pedidos, viewGroup, false);
            this.context = viewGroup.getContext();
            return new MyViewHolder(itemList);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
            PedidoTotal pt = listaPedidosTotais.get(position);
            myViewHolder.nomeValor.setText(pt.getPessoa().getNome());
            myViewHolder.enderecoValor.setText(String.valueOf(pt.getPessoa().getEndereco()));
            myViewHolder.valorTotalValor.setText("R$ "+String.valueOf(pt.getValorTotal()));
            myViewHolder.pedidosValor.setText(pt.getListaPedidosString());

            Bundle bundle = new Bundle();
            bundle.putString("id", String.valueOf(listaPedidosTotais.get(position).getId()));

        }

        @Override
        public int getItemCount() {
            return listaPedidosTotais.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{
            TextView nomeValor;
            TextView valorTotalValor;
            TextView enderecoValor;
            TextView pedidosValor;


            public MyViewHolder(View itemView){
                super(itemView);
                nomeValor = itemView.findViewById(R.id.nomeValor);
                valorTotalValor = itemView.findViewById(R.id.valorTotalValor);
                enderecoValor = itemView.findViewById(R.id.enderecoValor);
                pedidosValor = itemView.findViewById(R.id.pedidosValor);

            }
        }
    }



package com.example.gasapp.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gasapp.model.Produto;

import java.util.ArrayList;
import java.util.List;
import com.example.gasapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        List<Produto> listaProdutos = new ArrayList<>();
        Context context;
        public MyAdapter(Context context, List<Produto> produtos) {
            this.context = context;
            this.listaProdutos = produtos;
        }


    @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View itemList = LayoutInflater.from(viewGroup.getContext()).
                    inflate(R.layout.adapter_card_icones, viewGroup, false);
            this.context = viewGroup.getContext();
            return new MyViewHolder(itemList);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
            final int[] qtdNumber = {0};
            Produto p = listaProdutos.get(position);
            myViewHolder.nome.setText(p.getNome());

            Bundle bundle = new Bundle();
            bundle.putString("id", String.valueOf(listaProdutos.get(position).getId()));

            myViewHolder.adicionar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    qtdNumber[0] += 1;
                    adicionar(myViewHolder, qtdNumber[0]);
                }
            });
            myViewHolder.remover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    qtdNumber[0] -= 1;
                    remover(myViewHolder, qtdNumber[0]);
                }
            });
        }

        @Override
        public int getItemCount() {
            return listaProdutos.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{
            TextView id;
            TextView nome;
            Button adicionar;
            Button remover;
            TextView qtd;
            Integer qtdNumber = 0;

            public MyViewHolder(View itemView){
                super(itemView);
                nome = itemView.findViewById(R.id.nomeProduto);
                adicionar = itemView.findViewById(R.id.adicionar);
                remover = itemView.findViewById(R.id.remover);
                qtd = itemView.findViewById(R.id.qtd);
                qtd.setText(String.valueOf(0));

            }
        }

        public void adicionar(MyViewHolder v, int qtdNumber) {
            TextView qtd = v.qtd;
            qtd.setText(String.valueOf(qtdNumber));
        }

        public void remover(MyViewHolder v,int qtdNumber) {
            if(qtdNumber >= 0){
                TextView qtd;
                qtd = v.qtd;
                qtd.setText(String.valueOf(qtdNumber));
            }
        }
    }



package com.example.gasapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gasapp.adapter.MyAdapter;
import com.example.gasapp.model.Pedido;
import com.example.gasapp.model.PedidoTotal;
import com.example.gasapp.model.Pessoa;
import com.example.gasapp.model.Produto;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ListarProdutosFragment extends Fragment {
    List<Produto> listaProdutos = new ArrayList<>();
    MyAdapter myAdapter;
    RecyclerView recyclerView;
    View root;
    Button fecharPedido;
    List<Pedido> listaPedidos = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_listar_produtos, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        fecharPedido = root.findViewById(R.id.fecharPedido);
        carregaProdutos();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);

        fecharPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fecharPedido();
            }
        });

        return root;
    }
    private void carregaProdutos(){
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("produtos");
        listaProdutos = new ArrayList<>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Produto produto = ds.getValue(Produto.class);
                    produto.setId(ds.getKey());
                    listaProdutos.add(produto);
                }
                myAdapter = new MyAdapter(root.getContext(), listaProdutos);
                recyclerView.setAdapter(myAdapter);
                recyclerView.setHasFixedSize(true);
                reference.removeEventListener(this);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private  void fecharPedido(){
        View child;
        double totalQtd = 0;
        listaPedidos.clear();
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            child = recyclerView.getChildAt(i);
            String qtd = ((TextView)child.findViewById(R.id.qtd)).getText().toString();
            int qtdNumber = Integer.parseInt(qtd);
            if(qtdNumber > 0){
                String produto = ((TextView)child.findViewById(R.id.nomeProduto)).getText().toString();
                Double produtoPreco  = Double.parseDouble(((TextView)child.findViewById(R.id.precoProduto)).getText().toString());
                Integer produtoQtd = Integer.parseInt(qtd);
                Double produtoTotalPreco = produtoPreco * produtoQtd;
                Pedido pedido = new Pedido(produto,qtd, produtoTotalPreco);
                totalQtd += produtoTotalPreco;
                listaPedidos.add(pedido);
            }
        }
        if(!listaPedidos.isEmpty()){
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            DatabaseReference userNameRef = rootRef.child("pessoa");
            double finalTotalQtd = totalQtd;
            ValueEventListener eventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(!dataSnapshot.exists()) {
                        Snackbar.make(root, "Não existe um endereco cadastrado, cadastre um antes", Snackbar.LENGTH_LONG).show();
                    } else {

                        Pessoa pessoa = dataSnapshot.getValue(Pessoa.class);
                        PedidoTotal pedidoTotal = new PedidoTotal(listaPedidos, finalTotalQtd, pessoa);
                        savePedidoBD(pedidoTotal);
                        Snackbar.make(root, "fechando pedido - " + pedidoTotal, Snackbar.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {}
            };
            userNameRef.addListenerForSingleValueEvent(eventListener);

        }

    }

    private  void savePedidoBD(PedidoTotal pedidoTotal){
        DatabaseReference pedidos = FirebaseDatabase.getInstance().getReference().child("pedidosCompletos");
        pedidos.push().setValue(pedidoTotal).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Snackbar.make(root, "Cadastrado com sucesso", Snackbar.LENGTH_LONG).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(root, "Erro ao cadastrar pedido!", Snackbar.LENGTH_LONG)
                                .setTextColor(Color.RED).show();
                    }
                });
    }
}
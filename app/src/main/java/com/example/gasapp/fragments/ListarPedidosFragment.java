package com.example.gasapp.fragments;

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

import com.example.gasapp.R;
import com.example.gasapp.adapter.MyAdapter;
import com.example.gasapp.adapter.MyAdapterPedidos;
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


public class ListarPedidosFragment extends Fragment {
    List<PedidoTotal> listaPedidosTotais = new ArrayList<>();
    MyAdapterPedidos myAdapter;
    RecyclerView recyclerView;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_listar_pedidos, container, false);
        recyclerView = root.findViewById(R.id.recyclerViewPedidos);
        carregaPedidos();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);

        return root;
    }
    private void carregaPedidos(){
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("pedidosCompletos");
        listaPedidosTotais = new ArrayList<>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    PedidoTotal pedidoTotal = ds.getValue(PedidoTotal.class);
                    pedidoTotal.setId(ds.getKey());
                    listaPedidosTotais.add(pedidoTotal);
                }
                myAdapter = new MyAdapterPedidos(root.getContext(), listaPedidosTotais);
                recyclerView.setAdapter(myAdapter);
                recyclerView.setHasFixedSize(true);
                reference.removeEventListener(this);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
package com.example.gasapp.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gasapp.R;
import com.example.gasapp.model.Pessoa;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserFragment extends Fragment {
    private TextInputEditText nome, endereco,dataNasc;
    private TextInputLayout nomeLayout, enderecoLayout, dataNascLayout;
    private Button btnCadastrar;
    View root;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_user, container, false);
        nome = root.findViewById(R.id.txtNome);
        endereco =  root.findViewById((R.id.txtEnd));
        nomeLayout = root.findViewById(R.id.layoutNome);
        enderecoLayout =  root.findViewById((R.id.layoutEndereco));
        btnCadastrar = root.findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadPessoa(view);
            }
        });
        return root;
    }
    public void cadPessoa(View view){
        if(validarCampos()){
            String nome = ((EditText)root.findViewById(R.id.txtNome)).getText().toString();
            String endereco = ((EditText)root.findViewById(R.id.txtEnd)).getText().toString();

            Pessoa pessoa = new Pessoa(nome, endereco);
            DatabaseReference pessoas = FirebaseDatabase.getInstance().getReference().child("pessoa");
            pessoas.setValue(pessoa).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Snackbar.make(view, "Cadastrado com sucesso", Snackbar.LENGTH_LONG).show();
                    Navigation.findNavController(view).navigate(R.id.action_userFragment_to_nav_home);
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Snackbar.make(view, "Erro ao cadastrar usuario!", Snackbar.LENGTH_LONG)
                                    .setTextColor(Color.RED).show();
                        }
                    });
        }

    }
    private boolean validarCampos(){
        if(nome.getText().toString().isEmpty()){
            nomeLayout.setErrorEnabled(true);
            nomeLayout.setError("Informe o seu nome");
            return false;
        }else{
            nomeLayout.setErrorEnabled(false);
        }

        if(endereco.getText().toString().isEmpty()){
            enderecoLayout.setErrorEnabled(true);
            enderecoLayout.setError("Informe o seu endereço");
            return false;
        }else{
            enderecoLayout.setErrorEnabled(false);
        }

        return true;
    }
}
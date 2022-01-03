package com.example.gasapp.model;

public class Produto {
    private String id;
    private String nome;
    private Double preco;


    public Produto(){}
    public Produto(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public Double getPreco() {   return preco;   }

    public void setPreco(Double preco) {    this.preco = preco; }


    public String getId() {   return id;   }

    public void setId(String id) {    this.id = id; }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                '}';
    }


}

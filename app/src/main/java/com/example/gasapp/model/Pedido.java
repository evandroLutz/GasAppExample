package com.example.gasapp.model;

public class Pedido {
    private String produto;
    private String qtd;
    private Double valor;


    public Pedido(){}
    public Pedido(String produto, String qtd, Double valor) {
        this.produto = produto;
        this.qtd = qtd;
        this.valor = valor;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }



    public String getQtd() {
        return qtd;
    }

    public void setQtd(String qtd) {
        this.qtd = qtd;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

}

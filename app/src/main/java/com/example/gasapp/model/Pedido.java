package com.example.gasapp.model;

public class Pedido {
    private String produto;
    private String qtd;


    public Pedido(){}
    public Pedido(String produto, String qtd) {
        this.produto = produto;
        this.qtd = qtd;
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

}

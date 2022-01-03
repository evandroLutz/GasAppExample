package com.example.gasapp.model;

import java.util.ArrayList;
import java.util.List;

public class PedidoTotal {
    private List<Pedido> listaPedidos = new ArrayList<>();
    private Pessoa pessoa;
    private Double valorTotal;


    public PedidoTotal(){}
    public PedidoTotal(List<Pedido> listaPedidos, Double valorTotal, Pessoa pessoa) {
        this.listaPedidos = listaPedidos;
        this.pessoa = pessoa;
        this.valorTotal = valorTotal;
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}

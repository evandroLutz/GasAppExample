package com.example.gasapp.model;

import java.util.ArrayList;
import java.util.List;

public class PedidoTotal {
    private String id;
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

    public String getListaPedidosString() {

        String pedidos = "produtos do pedido: \n\n";
        for(Pedido pedido : listaPedidos){
            pedidos += "- " + pedido.getProduto() + ", qtd: " + pedido.getQtd() + "\n valor total do produto: " + "\nR$ "+pedido.getValor() + "\n\n";
        }
        return pedidos;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public String getId() {
        return id;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setId(String id) {
        this.id = id;
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

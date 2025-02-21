package com.trabalhoapigateway.store.domain;

import java.util.UUID;

public class Componente {
    private UUID id;
    private UUID codigo;
    private String sku;
    private String descricao;
    private double preco;
    private int quantidade;

    public Componente(UUID id, UUID codigo, String sku, String descricao, double preco, int quantidade) {
        this.id = id;
        this.codigo = codigo;
        this.sku = sku;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCodigo() {
        return codigo;
    }

    public void setCodigo(UUID codigo) {
        this.codigo = codigo;
    }

    public String getSku() {
        return sku;
    }

    public void setsku(String sku) {
        this.sku = sku;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}

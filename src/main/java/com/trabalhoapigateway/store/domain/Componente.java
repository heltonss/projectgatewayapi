package com.trabalhoapigateway.store.domain;

public class Componente {
    private Integer id;
    private long codigo;
    private String SKU;
    private String descricao;
    private Double preco;
    private Integer quantidade;

    public Componente(Integer id, Long codigo, String SKU, String descricao, Double preco, Integer quantidade) {
        this.id = id;
        this.codigo = codigo;
        this.SKU = SKU;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}

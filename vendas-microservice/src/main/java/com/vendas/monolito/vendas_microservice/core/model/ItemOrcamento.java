package com.vendas.monolito.vendas_microservice.core.model;

import java.math.BigDecimal;


public class ItemOrcamento {

    private Long produtoId;
    private int quantidade;
    private BigDecimal precoUnitario;


    public ItemOrcamento(Long produtoId, int quantidade, BigDecimal precoUnitario){
        this.produtoId=produtoId;
        this.quantidade =quantidade;
        this.precoUnitario=precoUnitario;
    }

    public Long getProdutoId () {
        return produtoId;
    }

    public void setProdutoId ( Long produtoId ) {
        this.produtoId = produtoId;
    }

    public int getQuantidade () {
        return quantidade;
    }

    public void setQuantidade ( int quantidade ) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitario () {
        return precoUnitario;
    }

    public void setPrecoUnitario ( BigDecimal precoUnitario ) {
        this.precoUnitario = precoUnitario;
    }

    public BigDecimal getSubtotal() {
        return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }

    public String getDescricao() {

        return "";
    }
}

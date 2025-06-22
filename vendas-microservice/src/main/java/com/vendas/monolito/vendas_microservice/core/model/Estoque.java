package com.vendas.monolito.vendas_microservice.core.model;

public class Estoque {

    private Long produtoId;
    private int quantidadeAtual;
    private int quantidadeMinima;
    private int quantidadeMaxima;

    public Estoque(Long produtoId, int quantidadeAtual, int quantidadeMinima, int quantidadeMaxima){
        this.produtoId=produtoId;
        this.quantidadeAtual=quantidadeAtual;
        this.quantidadeMinima=quantidadeMinima;
        this.quantidadeMaxima=quantidadeMaxima;
    }

    //getters
    public Long getProdutoId() {
        return produtoId;
    }

    public int getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public int getQuantidadeMaxima() {
        return quantidadeMaxima;
    }
}

package com.vendas.monolito.vendas_microservice.core.model;

import java.math.BigDecimal;

public class Produto {

    private Long id;
    private String descricao;
    private BigDecimal precoUnitario;


    public Produto ( Long id, String description, BigDecimal price){
        this.id=id;
        this.descricao=description;
        this.precoUnitario = price;
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getDescricao () {
        return descricao;
    }

    public void setDescricao ( String descricao ) {
        this.descricao = descricao;
    }

    public BigDecimal getPrecoUnitario () {
        return precoUnitario;
    }

    public void setPrecoUnitario ( BigDecimal precoUnitario ) {
        this.precoUnitario = precoUnitario;
    }
}

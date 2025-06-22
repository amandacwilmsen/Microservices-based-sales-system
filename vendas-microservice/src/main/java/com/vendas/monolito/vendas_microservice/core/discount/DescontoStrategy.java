package com.vendas.monolito.vendas_microservice.core.discount;


import com.vendas.monolito.vendas_microservice.core.model.Orcamento;

public interface DescontoStrategy {
    double calcularDesconto( Orcamento orcamento);
}
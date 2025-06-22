package com.vendas.monolito.vendas_microservice.application.dto.mapper;

import com.vendas.monolito.vendas_microservice.application.dto.request.EstoqueRequest;
import com.vendas.monolito.vendas_microservice.core.model.Estoque;

public class EstoqueMapper {
    public static Estoque toCore(EstoqueRequest request) {
        return new Estoque(
            request.produtoId(),
            request.quantidadeAtual(),
            request.quantidadeMinima(),
            request.quantidadeMaxima()
        );
    }
}


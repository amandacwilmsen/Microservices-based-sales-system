package com.vendas.monolito.vendas_microservice.application.usecases;

import com.vendas.monolito.vendas_microservice.core.service.EstoqueService;

import java.util.List;
import java.util.Map;

public class ConsultarEstoqueUseCase {

    private final EstoqueService estoqueService;

    public ConsultarEstoqueUseCase(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    public Map<Long, Integer> executar(List<Long> ids) {
        return estoqueService.consultarEstoque(ids);
    }
}

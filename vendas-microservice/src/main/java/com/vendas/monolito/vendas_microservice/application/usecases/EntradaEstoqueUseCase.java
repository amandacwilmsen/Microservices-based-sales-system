package com.vendas.monolito.vendas_microservice.application.usecases;

import com.vendas.monolito.vendas_microservice.application.dto.mapper.EstoqueMapper;
import com.vendas.monolito.vendas_microservice.application.dto.request.EstoqueRequest;
import com.vendas.monolito.vendas_microservice.core.service.EstoqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntradaEstoqueUseCase {

    private final EstoqueService estoqueService;

    public void executar(EstoqueRequest entrada) {
        estoqueService.entradaEstoque(EstoqueMapper.toCore(entrada));
    }
}

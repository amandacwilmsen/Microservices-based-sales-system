package com.vendas.monolito.vendas_microservice.application.usecases;

import com.vendas.monolito.vendas_microservice.core.model.Estoque;
import com.vendas.monolito.vendas_microservice.core.repository.EstoqueInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarEstoqueUseCase {

    private final EstoqueInterface estoqueRepository;

    public List<Estoque> executar() {
        return estoqueRepository.buscarTodos();
    }
}


package com.vendas.monolito.vendas_microservice.core.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.vendas.monolito.vendas_microservice.core.model.Estoque;
import com.vendas.monolito.vendas_microservice.core.repository.EstoqueInterface;

public class EstoqueService {

    private final EstoqueInterface estoqueRepository;

    public EstoqueService(EstoqueInterface estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    public void entradaEstoque(Estoque estoque) {
        estoqueRepository.salvar(estoque);
    }

    public List<Estoque> listarEstoque() {
        return estoqueRepository.buscarTodos();
    }

    public Map<Long, Integer> consultarEstoque(List<Long> ids) {
        return ids.stream()
            .map(estoqueRepository::buscarPorProdutoId)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toMap(Estoque::getProdutoId, Estoque::getQuantidadeAtual));
    }
}


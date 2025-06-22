package com.vendas.monolito.vendas_microservice.core.repository;

import java.util.List;
import java.util.Optional;

import com.vendas.monolito.vendas_microservice.core.model.Estoque;

public interface EstoqueInterface {
    List<Estoque> buscarTodos();

    Optional<Estoque> buscarPorProdutoId(Long produtoId);

    Estoque salvar (Estoque estoque);

    void deletarPorProdutoId(Long produtoId);
}

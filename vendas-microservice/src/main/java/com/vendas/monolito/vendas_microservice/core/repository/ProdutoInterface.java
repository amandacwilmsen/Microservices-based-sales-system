package com.vendas.monolito.vendas_microservice.core.repository;


import com.vendas.monolito.vendas_microservice.core.model.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoInterface {
    List<Produto> buscarTodos();

    Optional<Produto> buscarPorId(Long id);

    Produto salvar(Produto produto);

    void deletarPorId(Long id);
}

package com.vendas.monolito.vendas_microservice.core.repository;

import com.vendas.monolito.vendas_microservice.core.model.Orcamento;

import java.util.List;
import java.util.Optional;


public interface OrcamentoInterface {
    List<Orcamento> buscarTodos();

    Optional<Orcamento> buscarPorId(Long id);

    Orcamento salvar(Orcamento orcamento);

    void deletar (Long id);

}

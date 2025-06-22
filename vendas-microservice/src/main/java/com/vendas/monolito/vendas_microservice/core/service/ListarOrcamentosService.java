package com.vendas.monolito.vendas_microservice.core.service;

import com.vendas.monolito.vendas_microservice.core.model.Orcamento;
import com.vendas.monolito.vendas_microservice.core.repository.OrcamentoInterface;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ListarOrcamentosService {

    private final OrcamentoInterface orcamentoRepository;

    public ListarOrcamentosService(OrcamentoInterface orcamentoRepository) {
        this.orcamentoRepository = orcamentoRepository;
    }

    public List<Orcamento> listarEfetivadosPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
        return orcamentoRepository.buscarTodos().stream()
                .filter(Orcamento::isEfetivado)
                .filter(o -> !o.getDataCriacao().isBefore(dataInicial) && !o.getDataCriacao().isAfter(dataFinal))
                .collect(Collectors.toList());
    }
}


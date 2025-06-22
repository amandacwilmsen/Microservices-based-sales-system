package com.vendas.monolito.vendas_microservice.application.usecases;

import com.vendas.monolito.vendas_microservice.application.dto.mapper.OrcamentoMapper;
import com.vendas.monolito.vendas_microservice.application.dto.response.OrcamentoResponse;
import com.vendas.monolito.vendas_microservice.core.service.ListarOrcamentosService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ListarOrcamentosEfetivadosUseCase {

    private final ListarOrcamentosService listarOrcamentosService;

    public ListarOrcamentosEfetivadosUseCase(ListarOrcamentosService listarOrcamentosService) {
        this.listarOrcamentosService = listarOrcamentosService;
    }

    public List<OrcamentoResponse> executar(LocalDate dataInicial, LocalDate dataFinal) {
        return listarOrcamentosService.listarEfetivadosPorPeriodo(dataInicial, dataFinal).stream()
                .map(OrcamentoMapper::toResponse)
                .collect(Collectors.toList());
    }
}

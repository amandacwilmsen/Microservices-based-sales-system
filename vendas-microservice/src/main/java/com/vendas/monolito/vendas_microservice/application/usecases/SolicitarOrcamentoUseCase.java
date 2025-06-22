package com.vendas.monolito.vendas_microservice.application.usecases;

import com.vendas.monolito.vendas_microservice.application.dto.mapper.OrcamentoMapper;
import com.vendas.monolito.vendas_microservice.application.dto.request.OrcamentoRequest;
import com.vendas.monolito.vendas_microservice.application.dto.response.OrcamentoResponse;
import com.vendas.monolito.vendas_microservice.core.service.SolicitarOrcamentoService;

public class SolicitarOrcamentoUseCase {

    private final SolicitarOrcamentoService solicitarOrcamentoService;

    public SolicitarOrcamentoUseCase ( SolicitarOrcamentoService solicitarOrcamentoService ) {
        this.solicitarOrcamentoService = solicitarOrcamentoService;
    }

    public OrcamentoResponse executar( OrcamentoRequest orcamentoRequest ){
        var orcamento = solicitarOrcamentoService.executar(OrcamentoMapper.toCore(orcamentoRequest));
        return OrcamentoMapper.toResponse(orcamento);
    }
}
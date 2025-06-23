package com.vendas.monolito.vendas_microservice.application.dto.mapper;

import com.vendas.monolito.vendas_microservice.application.dto.request.OrcamentoRequest;
import com.vendas.monolito.vendas_microservice.application.dto.response.OrcamentoResponse;
import com.vendas.monolito.vendas_microservice.core.model.Orcamento;

public class OrcamentoMapper {

    public static Orcamento toCore( OrcamentoRequest orcamentoRequest ){
        return new Orcamento(
                orcamentoRequest.cliente(),
                orcamentoRequest.estado(),
                orcamentoRequest.pais(),
                orcamentoRequest.itens()
        );
    }

    public static OrcamentoResponse toResponse( Orcamento orcamento ){
        return new OrcamentoResponse(
                orcamento.getId(),
                orcamento.getCliente(),
                orcamento.getDataCriacao(),
                orcamento.getEstado(),
                orcamento.getPais(),
                orcamento.getItens(),
                orcamento.isEfetivado(),
                orcamento.getTotalItens(),
                orcamento.getImpostoEstadual(), // Usando o método de compatibilidade
                orcamento.getImpostoFederal(), // Usando o método de compatibilidade
                orcamento.getDesconto(),
                orcamento.getTotalFinal()
        );
    }
}

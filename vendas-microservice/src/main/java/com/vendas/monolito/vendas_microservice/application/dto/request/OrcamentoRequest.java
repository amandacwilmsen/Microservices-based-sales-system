package com.vendas.monolito.vendas_microservice.application.dto.request;

import com.vendas.monolito.vendas_microservice.core.model.ItemOrcamento;

import java.util.List;

public record OrcamentoRequest (
     Long id,
     String cliente,
     String estado,
     String pais,
     List<ItemOrcamento>itens
){
}

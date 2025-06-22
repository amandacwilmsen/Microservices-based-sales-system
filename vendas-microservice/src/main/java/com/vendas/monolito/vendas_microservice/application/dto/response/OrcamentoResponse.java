package com.vendas.monolito.vendas_microservice.application.dto.response;

import com.vendas.monolito.vendas_microservice.core.model.ItemOrcamento;

import java.time.LocalDate;
import java.util.List;

public record OrcamentoResponse(
    Long id,
    String cliente,
    LocalDate dataCriacao,
    String estado,
    String pais,
    List<ItemOrcamento> itens,
    boolean efetivado,
    double totalItens,
    double impostoEstadual,
    double impostoFederal,
    double desconto,
    double totalFinal

){}
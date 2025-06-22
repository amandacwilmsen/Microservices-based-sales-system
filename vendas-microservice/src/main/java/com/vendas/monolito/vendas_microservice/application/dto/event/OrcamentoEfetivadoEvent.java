package com.vendas.monolito.vendas_microservice.application.dto.event;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public record OrcamentoEfetivadoEvent(
    Long orcamentoId,
    String cliente,
    LocalDate dataEfetivacao,
    List<Long> produtosIds,
    double valorTotal
) implements Serializable {}

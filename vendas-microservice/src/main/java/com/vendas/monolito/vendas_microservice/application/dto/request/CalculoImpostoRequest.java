package com.vendas.monolito.vendas_microservice.application.dto.request;

import java.math.BigDecimal;

public record CalculoImpostoRequest(
    BigDecimal valorBase,
    String estadoDestino
) {}


package com.vendas.impostos.application.dto;

import java.math.BigDecimal;

public record CalculoImpostoRequest(
    BigDecimal valor,
    String estado,
    String pais
) {}

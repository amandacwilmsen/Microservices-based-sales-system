package com.vendas.impostos.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CalculoImpostoResponse(
    BigDecimal valorBase,
    BigDecimal valorImpostos,
    BigDecimal valorTotal,
    LocalDateTime dataCalculo,
    String instanciaServico,
    DetalheImpostos detalhes
) {
    
    public record DetalheImpostos(
        BigDecimal icms,
        BigDecimal ipi,
        BigDecimal pis,
        BigDecimal cofins,
        BigDecimal aliquotaTotal
    ) {}
}

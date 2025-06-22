package com.vendas.impostos.application.dto;

public record CalculoImpostoResponse(
    double valorImpostoEstadual,
    double valorImpostoFederal
) {}

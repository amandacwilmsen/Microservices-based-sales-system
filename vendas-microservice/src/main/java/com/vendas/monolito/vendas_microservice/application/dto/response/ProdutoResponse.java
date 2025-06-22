package com.vendas.monolito.vendas_microservice.application.dto.response;

import java.math.BigDecimal;

public record ProdutoResponse(
        Long id,
        String descricao,
        BigDecimal precoUnitario
)
{}

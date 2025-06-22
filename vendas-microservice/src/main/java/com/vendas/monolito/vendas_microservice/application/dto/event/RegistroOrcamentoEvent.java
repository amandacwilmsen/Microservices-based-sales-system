package com.vendas.monolito.vendas_microservice.application.dto.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RegistroOrcamentoEvent(
    LocalDateTime data,
    BigDecimal valorFinal,
    BigDecimal valorImpostos
) {}


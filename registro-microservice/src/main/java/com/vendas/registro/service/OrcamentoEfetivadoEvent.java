package com.vendas.registro.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record OrcamentoEfetivadoEvent(
    Long orcamentoId,
    LocalDate dataEfetivacao,
    BigDecimal valorTotal,
    BigDecimal valorImpostos
) implements Serializable {}

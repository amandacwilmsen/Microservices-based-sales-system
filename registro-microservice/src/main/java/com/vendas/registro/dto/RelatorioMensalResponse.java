package com.vendas.registro.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RelatorioMensalResponse(
    int ano,
    int mes,
    String mesNome,
    BigDecimal totalVendido,
    BigDecimal totalImpostos,
    int quantidadeVendas,
    LocalDateTime dataGeracao
) {}

package com.vendas.registro.dto;

import jakarta.validation.constraints.NotNull;

public record ConsultaPeriodoRequest(
    @NotNull(message = "Ano é obrigatório")
    Integer ano,
    
    @NotNull(message = "Mês é obrigatório")
    Integer mes
) {
    
    public ConsultaPeriodoRequest {
        if (ano < 2020 || ano > 2030) {
            throw new IllegalArgumentException("Ano deve estar entre 2020 e 2030");
        }
        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException("Mês deve estar entre 1 e 12");
        }
    }
}

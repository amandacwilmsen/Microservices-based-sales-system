package com.vendas.impostos.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record CalculoImpostoRequest(
    @NotNull(message = "Valor base é obrigatório")
    @DecimalMin(value = "0.01", message = "Valor base deve ser maior que zero")
    BigDecimal valorBase,
    
    @NotNull(message = "Tipo de produto é obrigatório")
    TipoProduto tipoProduto,
    
    String estadoDestino
) {
    
    public enum TipoProduto {
        ELETRÔNICOS,
        ROUPAS,
        LIVROS,
        ALIMENTOS,
        MEDICAMENTOS,
        OUTROS
    }
}

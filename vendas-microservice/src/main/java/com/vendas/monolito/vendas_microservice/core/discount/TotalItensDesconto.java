package com.vendas.monolito.vendas_microservice.core.discount;


import java.math.BigDecimal;

import com.vendas.monolito.vendas_microservice.core.model.Orcamento;

public class TotalItensDesconto implements DescontoStrategy {

    @Override
    public double calcularDesconto(Orcamento orcamento) {
        if (orcamento.getItens() != null && orcamento.getItens().size() > 10) {
            BigDecimal desconto = orcamento.getSubtotal().multiply(BigDecimal.valueOf(0.10));
            return desconto.doubleValue();
        }
        return 0.0;
    }
}

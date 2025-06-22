package com.vendas.monolito.vendas_microservice.core.discount;


import com.vendas.monolito.vendas_microservice.core.model.ItemOrcamento;
import com.vendas.monolito.vendas_microservice.core.model.Orcamento;

import java.math.BigDecimal;

public class QuantidadeItemDesconto implements DescontoStrategy {

    @Override
    public double calcularDesconto( Orcamento orcamento) {
        BigDecimal desconto = BigDecimal.ZERO;

        for (ItemOrcamento item : orcamento.getItens()) {
            if (item.getQuantidade() > 3) {
                desconto = desconto.add(item.getSubtotal().multiply(BigDecimal.valueOf(0.05)));
            }
        }
        return desconto.doubleValue();
    }
}
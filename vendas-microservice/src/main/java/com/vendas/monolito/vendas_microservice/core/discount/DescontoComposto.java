package com.vendas.monolito.vendas_microservice.core.discount;


import com.vendas.monolito.vendas_microservice.core.model.Orcamento;

import java.util.List;

public class DescontoComposto implements DescontoStrategy {
    private final List<DescontoStrategy> strategies;

    public DescontoComposto(List<DescontoStrategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public double calcularDesconto( Orcamento orcamento) {
        return strategies.stream()
                .mapToDouble(s -> s.calcularDesconto(orcamento))
                .sum();
    }
}

package com.vendas.monolito.vendas_microservice.core.service;

import com.vendas.monolito.vendas_microservice.core.discount.DescontoStrategy;
import com.vendas.monolito.vendas_microservice.core.model.Orcamento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalcularDescontoService {

    private static final Logger logger = LoggerFactory.getLogger(CalcularDescontoService.class);
    private final DescontoStrategy descontoStrategy;

    public CalcularDescontoService(DescontoStrategy descontoStrategy){
        this.descontoStrategy = descontoStrategy;
    }

    public double executar( Orcamento orcamento){
        logger.info("Calculando desconto...");
        return descontoStrategy.calcularDesconto(orcamento);
    }
}

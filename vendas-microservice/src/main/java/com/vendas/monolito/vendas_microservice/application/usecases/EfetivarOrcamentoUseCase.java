package com.vendas.monolito.vendas_microservice.application.usecases;

import com.vendas.monolito.vendas_microservice.core.model.Estoque;
import com.vendas.monolito.vendas_microservice.core.model.ItemOrcamento;
import com.vendas.monolito.vendas_microservice.core.model.Orcamento;
import com.vendas.monolito.vendas_microservice.core.repository.EstoqueInterface;
import com.vendas.monolito.vendas_microservice.core.repository.OrcamentoInterface;
import com.vendas.monolito.vendas_microservice.core.repository.ProdutoInterface;
import com.vendas.monolito.vendas_microservice.core.service.EfetivarOrcamentoService;

import java.time.LocalDate;

public class EfetivarOrcamentoUseCase {

    private final EfetivarOrcamentoService efetivarOrcamentoService;

    public EfetivarOrcamentoUseCase ( EfetivarOrcamentoService efetivarOrcamentoService ) {
        this.efetivarOrcamentoService = efetivarOrcamentoService;
    }

    public void executar( Long id){
        efetivarOrcamentoService.executar(id);
    }
}

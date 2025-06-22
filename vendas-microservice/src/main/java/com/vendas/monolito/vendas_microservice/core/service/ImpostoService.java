package com.vendas.monolito.vendas_microservice.core.service;

import com.vendas.monolito.vendas_microservice.core.model.Orcamento;
import com.vendas.monolito.vendas_microservice.core.model.ValoresImposto;

public interface ImpostoService {
    ValoresImposto calcularImposto(Orcamento orcamento);
}

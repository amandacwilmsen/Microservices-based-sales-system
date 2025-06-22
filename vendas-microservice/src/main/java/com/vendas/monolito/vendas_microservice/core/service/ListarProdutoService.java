package com.vendas.monolito.vendas_microservice.core.service;

import com.vendas.monolito.vendas_microservice.core.model.Produto;
import com.vendas.monolito.vendas_microservice.core.repository.ProdutoInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ListarProdutoService {

    private static final Logger logger = LoggerFactory.getLogger(ListarProdutoService.class);
    private final ProdutoInterface produtoInterface;

    public ListarProdutoService ( ProdutoInterface produtoInterface ) {
        this.produtoInterface = produtoInterface;
    }

    public List<Produto> executar(){
        logger.info("Listando produtos...");
        return produtoInterface.buscarTodos();
    }
}

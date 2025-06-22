package com.vendas.monolito.vendas_microservice.application.usecases;


import com.vendas.monolito.vendas_microservice.application.dto.mapper.ProdutoMapper;
import com.vendas.monolito.vendas_microservice.application.dto.response.ProdutoResponse;
import com.vendas.monolito.vendas_microservice.core.service.ListarProdutoService;

import java.util.List;
import java.util.stream.Collectors;

public class ListarProdutoUseCase {

    private final ListarProdutoService listarProdutoService;

    public ListarProdutoUseCase ( ListarProdutoService listarProdutoService ) {
        this.listarProdutoService = listarProdutoService;
    }

    public List<ProdutoResponse> listarProdutos(){
        return listarProdutoService.executar()
                .stream()
                .map(ProdutoMapper::toResponse)
                .collect(Collectors.toList());
    }
}

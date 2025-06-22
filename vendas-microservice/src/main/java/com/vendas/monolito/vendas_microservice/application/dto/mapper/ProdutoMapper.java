package com.vendas.monolito.vendas_microservice.application.dto.mapper;

import com.vendas.monolito.vendas_microservice.core.model.Produto;
import com.vendas.monolito.vendas_microservice.application.dto.response.ProdutoResponse;
public class ProdutoMapper {

    public static ProdutoResponse toResponse( Produto produto){
        return new ProdutoResponse(
                produto.getId(),
                produto.getDescricao(),
                produto.getPrecoUnitario()
        );
    }

}

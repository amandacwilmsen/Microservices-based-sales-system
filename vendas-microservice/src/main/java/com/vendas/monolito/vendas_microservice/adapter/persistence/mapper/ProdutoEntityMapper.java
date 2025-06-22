package com.vendas.monolito.vendas_microservice.adapter.persistence.mapper;

import com.vendas.monolito.vendas_microservice.adapter.persistence.entity.ProdutoEntity;
import com.vendas.monolito.vendas_microservice.core.model.Produto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoEntityMapper {

    public ProdutoEntity toEntity(Produto produto) {
        if (produto == null) {
            return null;
        }
        return new ProdutoEntity(
                produto.getId(),
                produto.getDescricao(),
                produto.getPrecoUnitario()
        );
    }

    public Produto toDomain(ProdutoEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Produto(
                entity.getId(),
                entity.getDescricao(),
                entity.getPrecoUnitario()
        );
    }

    public List<Produto> toDomain(List<ProdutoEntity> entityList) {
        return entityList.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
}


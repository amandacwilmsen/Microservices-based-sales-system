package com.vendas.monolito.vendas_microservice.adapter.persistence.mapper;

import com.vendas.monolito.vendas_microservice.adapter.persistence.entity.EstoqueEntity;
import com.vendas.monolito.vendas_microservice.adapter.persistence.entity.ProdutoEntity;
import com.vendas.monolito.vendas_microservice.core.model.Estoque;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EstoqueEntityMapper {

    public Estoque toDomain(EstoqueEntity entity) {
        if (entity == null || entity.getProduto() == null) {
            return null;
        }
        return new Estoque(
                entity.getProduto().getId(),
                entity.getQuantidadeAtual(),
                entity.getQuantidadeMin(),
                entity.getQuantidadeMax()
        );
    }

    public EstoqueEntity toEntity(Estoque domain) {
        if (domain == null) {
            return null;
        }
        EstoqueEntity entity = new EstoqueEntity();
        ProdutoEntity produto = new ProdutoEntity();
        produto.setId(domain.getProdutoId());
        entity.setProduto(produto);
        entity.setQuantidadeAtual(domain.getQuantidadeAtual());
        entity.setQuantidadeMin(domain.getQuantidadeMinima());
        entity.setQuantidadeMax(domain.getQuantidadeMaxima());
        return entity;
    }

    public List<Estoque> toDomain(List<EstoqueEntity> entityList) {
        return entityList.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
}

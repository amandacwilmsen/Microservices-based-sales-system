package com.vendas.monolito.vendas_microservice.adapter.persistence.mapper;

import com.vendas.monolito.vendas_microservice.adapter.persistence.entity.ItemOrcamentoEntity;
import com.vendas.monolito.vendas_microservice.core.model.ItemOrcamento;
import org.springframework.stereotype.Component;

@Component
public class ItemOrcamentoEntityMapper {

    public ItemOrcamentoEntity toEntity(ItemOrcamento domain) {
        if (domain == null) {
            return null;
        }
        return new ItemOrcamentoEntity(
                domain.getProdutoId(),
                domain.getQuantidade(),
                domain.getPrecoUnitario()
        );
    }

    public ItemOrcamento toDomain(ItemOrcamentoEntity entity) {
        if (entity == null) {
            return null;
        }
        return new ItemOrcamento(
                entity.getProdutoId(),
                entity.getQuantidade(),
                entity.getPrecoUnitario()
        );
    }
}


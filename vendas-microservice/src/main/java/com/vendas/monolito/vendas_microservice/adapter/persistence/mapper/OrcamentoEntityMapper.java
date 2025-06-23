package com.vendas.monolito.vendas_microservice.adapter.persistence.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.vendas.monolito.vendas_microservice.adapter.persistence.entity.OrcamentoEntity;
import com.vendas.monolito.vendas_microservice.core.model.Orcamento;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrcamentoEntityMapper {

    private final ItemOrcamentoEntityMapper itemOrcamentoEntityMapper;

    public OrcamentoEntity toEntity(Orcamento orcamento) {
        if (orcamento == null) {
            return null;
        }

        OrcamentoEntity orcamentoEntity = new OrcamentoEntity();
        orcamentoEntity.setId(orcamento.getId());
        orcamentoEntity.setData(orcamento.getDataCriacao());
        orcamentoEntity.setNomeCliente(orcamento.getCliente());
        orcamentoEntity.setEstado(orcamento.getEstado());
        orcamentoEntity.setPais(orcamento.getPais());
        orcamentoEntity.setEfetivado(orcamento.isEfetivado());
        if (orcamento.getItens() != null) {
            orcamentoEntity.setItens(orcamento.getItens().stream()
                    .map(itemOrcamentoEntityMapper::toEntity)
                    .collect(Collectors.toList()));
            orcamentoEntity.getItens().forEach(item -> item.setOrcamento(orcamentoEntity));
        }
        orcamentoEntity.setTotalItens(orcamento.getTotalItens());
        // Dividindo o valor total de impostos entre estadual e federal para compatibilidade
        double metadeImpostos = orcamento.getValorImpostos() / 2.0;
        orcamentoEntity.setImpostoEstadual(metadeImpostos);
        orcamentoEntity.setImpostoFederal(metadeImpostos);
        orcamentoEntity.setDesconto(orcamento.getDesconto());
        orcamentoEntity.setTotalFinal(orcamento.getTotalFinal());
        return orcamentoEntity;
    }

    public Orcamento toCore(OrcamentoEntity entity) {
        if (entity == null) {
            return null;
        }

        Orcamento orcamento = new Orcamento(
                entity.getId(),
                entity.getNomeCliente(),
                entity.getData(),
                entity.getEstado(),
                entity.getPais(),
                entity.getItens().stream()
                        .map(itemOrcamentoEntityMapper::toDomain)
                        .collect(Collectors.toList()),
                entity.isEfetivado(),
                entity.getTotalItens(),
                // Somando impostoEstadual e impostoFederal para obter valorImpostos
                entity.getImpostoEstadual() + entity.getImpostoFederal(),
                entity.getDesconto(),
                entity.getTotalFinal()
        );
        
        return orcamento;
    }
}

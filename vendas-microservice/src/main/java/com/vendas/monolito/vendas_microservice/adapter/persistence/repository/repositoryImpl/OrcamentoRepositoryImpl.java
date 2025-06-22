package com.vendas.monolito.vendas_microservice.adapter.persistence.repository.repositoryImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.vendas.monolito.vendas_microservice.adapter.persistence.entity.OrcamentoEntity;
import com.vendas.monolito.vendas_microservice.adapter.persistence.mapper.OrcamentoEntityMapper;
import com.vendas.monolito.vendas_microservice.adapter.persistence.repository.jpaInterfaces.OrcamentoJPaRepository;
import com.vendas.monolito.vendas_microservice.core.model.Orcamento;
import com.vendas.monolito.vendas_microservice.core.repository.OrcamentoInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class OrcamentoRepositoryImpl implements OrcamentoInterface {

    private final OrcamentoJPaRepository orcamentoJpaRepository;
    private final OrcamentoEntityMapper orcamentoEntityMapper;

    @Override
    public List<Orcamento> buscarTodos () {
                return orcamentoJpaRepository
                .findAll()
                .stream()
                .map(orcamentoEntityMapper::toCore)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Orcamento> buscarPorId ( Long id ) {
        return orcamentoJpaRepository.findById(id).map(orcamentoEntityMapper::toCore);
    }

    @Override
    public Orcamento salvar ( Orcamento orcamento ) {
        OrcamentoEntity orcamentoEntity = orcamentoEntityMapper.toEntity(orcamento);
        OrcamentoEntity salvo = orcamentoJpaRepository.save(orcamentoEntity);
        return orcamentoEntityMapper.toCore(salvo);
    }

    @Override
    public void deletar ( Long id ) {
        orcamentoJpaRepository.deleteById(id);
    }
}

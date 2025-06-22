package com.vendas.monolito.vendas_microservice.adapter.persistence.repository.repositoryImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.vendas.monolito.vendas_microservice.adapter.persistence.entity.ProdutoEntity;
import com.vendas.monolito.vendas_microservice.adapter.persistence.mapper.ProdutoEntityMapper;
import com.vendas.monolito.vendas_microservice.adapter.persistence.repository.jpaInterfaces.ProdutoJPaRepository;
import com.vendas.monolito.vendas_microservice.core.model.Produto;
import com.vendas.monolito.vendas_microservice.core.repository.ProdutoInterface;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class ProdutoRepositoryImpl implements ProdutoInterface {

    private final ProdutoJPaRepository produtoJPaRepository;
    private final ProdutoEntityMapper mapper;

    @Override
    public List<Produto> buscarTodos() {
        List<ProdutoEntity> entities = produtoJPaRepository.findAll();
        return mapper.toDomain(entities);
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        return produtoJPaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Produto salvar(Produto produto) {
        ProdutoEntity entity = mapper.toEntity(produto);
        ProdutoEntity savedEntity = produtoJPaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public void deletarPorId(Long id) {
        produtoJPaRepository.deleteById(id);
    }
}

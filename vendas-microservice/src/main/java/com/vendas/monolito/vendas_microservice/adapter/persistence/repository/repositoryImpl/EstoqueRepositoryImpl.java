package com.vendas.monolito.vendas_microservice.adapter.persistence.repository.repositoryImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.vendas.monolito.vendas_microservice.adapter.persistence.entity.EstoqueEntity;
import com.vendas.monolito.vendas_microservice.adapter.persistence.entity.ProdutoEntity;
import com.vendas.monolito.vendas_microservice.adapter.persistence.repository.jpaInterfaces.EstoqueJPaRepository;
import com.vendas.monolito.vendas_microservice.core.model.Estoque;
import com.vendas.monolito.vendas_microservice.core.repository.EstoqueInterface;

@Repository
public class EstoqueRepositoryImpl implements EstoqueInterface {

    private final EstoqueJPaRepository jpa;

    public EstoqueRepositoryImpl(EstoqueJPaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public List<Estoque> buscarTodos() {
        return jpa.findAll().stream().map(this::entityParaDominio).collect(Collectors.toList());
    }

    @Override
    public Optional<Estoque> buscarPorProdutoId(Long produtoId) {
        return jpa.findById(produtoId).map(this::entityParaDominio);
    }

    @Override
    public Estoque salvar(Estoque estoque) {
        Optional<EstoqueEntity> existente = jpa.findAll().stream()
            .filter(e -> e.getProduto() != null && e.getProduto().getId() != null && e.getProduto().getId().equals(estoque.getProdutoId()))
            .findFirst();
        EstoqueEntity entity = dominioParaEntity(estoque);
        existente.ifPresent(e -> entity.setId(e.getId()));
        EstoqueEntity salvo = jpa.save(entity);
        return entityParaDominio(salvo);
    }

    @Override
    public void deletarPorProdutoId(Long produtoId) {
        jpa.deleteById(produtoId);
    }

    private Estoque entityParaDominio(EstoqueEntity e) {
        if (e == null || e.getProduto() == null) return null;
        return new Estoque(
                e.getProduto().getId(),
                e.getQuantidadeAtual(),
                e.getQuantidadeMin(),
                e.getQuantidadeMax()
        );
    }

    private EstoqueEntity dominioParaEntity(Estoque estoque) {
        EstoqueEntity e = new EstoqueEntity();
        ProdutoEntity p = new ProdutoEntity();
        p.setId(estoque.getProdutoId());
        e.setProduto(p);
        e.setQuantidadeAtual(estoque.getQuantidadeAtual());
        e.setQuantidadeMin(estoque.getQuantidadeMinima());
        e.setQuantidadeMax(estoque.getQuantidadeMaxima());
        return e;
    }
}

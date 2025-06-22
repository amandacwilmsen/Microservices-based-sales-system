package com.vendas.monolito.vendas_microservice.adapter.persistence.repository.jpaInterfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vendas.monolito.vendas_microservice.adapter.persistence.entity.EstoqueEntity;

@Repository
public interface EstoqueJPaRepository extends JpaRepository<EstoqueEntity, Long> {
    // Interface padrão JpaRepository para EstoqueEntity
}

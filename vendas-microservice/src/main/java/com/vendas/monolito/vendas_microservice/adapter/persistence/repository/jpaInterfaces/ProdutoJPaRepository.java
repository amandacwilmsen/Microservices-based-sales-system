package com.vendas.monolito.vendas_microservice.adapter.persistence.repository.jpaInterfaces;

import com.vendas.monolito.vendas_microservice.adapter.persistence.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoJPaRepository extends JpaRepository<ProdutoEntity, Long>{

    
} 

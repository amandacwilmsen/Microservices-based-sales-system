package com.vendas.monolito.vendas_microservice.adapter.persistence.repository.jpaInterfaces;

import java.time.LocalDate;
import java.util.List;

import com.vendas.monolito.vendas_microservice.adapter.persistence.entity.OrcamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrcamentoJPaRepository extends JpaRepository<OrcamentoEntity, Long> {
    @Query("SELECT o FROM OrcamentoEntity o WHERE o.data >= :dataInicial AND o.data <= :dataFinal AND o.efetivado = true")
    List<OrcamentoEntity> findEfetivadosByPeriodo(@Param("dataInicial") LocalDate dataInicial, @Param("dataFinal") LocalDate dataFinal);
}

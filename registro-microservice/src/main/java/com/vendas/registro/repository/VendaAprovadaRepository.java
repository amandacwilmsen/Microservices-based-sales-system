package com.vendas.registro.repository;

import com.vendas.registro.entity.VendaAprovada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface VendaAprovadaRepository extends JpaRepository<VendaAprovada, Long> {

    @Query("""
           SELECT COALESCE(SUM(v.valorCobrado), 0) 
           FROM VendaAprovada v 
           WHERE YEAR(v.dataVenda) = :ano 
           AND MONTH(v.dataVenda) = :mes
           """)
    BigDecimal findTotalVendidoPorMes(@Param("ano") int ano, @Param("mes") int mes);

    @Query("""
           SELECT COALESCE(SUM(v.valorImpostos), 0) 
           FROM VendaAprovada v 
           WHERE YEAR(v.dataVenda) = :ano 
           AND MONTH(v.dataVenda) = :mes
           """)
    BigDecimal findTotalImpostosPorMes(@Param("ano") int ano, @Param("mes") int mes);

    @Query("""
           SELECT COUNT(v) 
           FROM VendaAprovada v 
           WHERE YEAR(v.dataVenda) = :ano 
           AND MONTH(v.dataVenda) = :mes
           """)
    long countVendasPorMes(@Param("ano") int ano, @Param("mes") int mes);

    List<VendaAprovada> findByDataVendaBetweenOrderByDataVendaDesc(
        java.time.LocalDate dataInicio, java.time.LocalDate dataFim);
}

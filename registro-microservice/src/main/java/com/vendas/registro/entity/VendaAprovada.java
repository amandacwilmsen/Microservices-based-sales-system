package com.vendas.registro.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "vendas_aprovadas")
public class VendaAprovada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String numeroOrcamento;

    @Column(nullable = false)
    private LocalDate dataVenda;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal valorCobrado;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal valorImpostos;

    @Column(nullable = false)
    private LocalDateTime dataRegistro;

    @Column(length = 500)
    private String observacoes;

    // Constructors
    public VendaAprovada() {}

    public VendaAprovada(String numeroOrcamento, LocalDate dataVenda, 
                        BigDecimal valorCobrado, BigDecimal valorImpostos) {
        this.numeroOrcamento = numeroOrcamento;
        this.dataVenda = dataVenda;
        this.valorCobrado = valorCobrado;
        this.valorImpostos = valorImpostos;
        this.dataRegistro = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroOrcamento() { return numeroOrcamento; }
    public void setNumeroOrcamento(String numeroOrcamento) { this.numeroOrcamento = numeroOrcamento; }

    public LocalDate getDataVenda() { return dataVenda; }
    public void setDataVenda(LocalDate dataVenda) { this.dataVenda = dataVenda; }

    public BigDecimal getValorCobrado() { return valorCobrado; }
    public void setValorCobrado(BigDecimal valorCobrado) { this.valorCobrado = valorCobrado; }

    public BigDecimal getValorImpostos() { return valorImpostos; }
    public void setValorImpostos(BigDecimal valorImpostos) { this.valorImpostos = valorImpostos; }

    public LocalDateTime getDataRegistro() { return dataRegistro; }
    public void setDataRegistro(LocalDateTime dataRegistro) { this.dataRegistro = dataRegistro; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}

package com.vendas.registro.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrcamentoAprovadoHistorico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataAprovacao;
    private double valorCobrado;
    private double valorImpostos;

    public OrcamentoAprovadoHistorico() {}

    public OrcamentoAprovadoHistorico(LocalDate dataAprovacao, double valorCobrado, double valorImpostos) {
        this.dataAprovacao = dataAprovacao;
        this.valorCobrado = valorCobrado;
        this.valorImpostos = valorImpostos;
    }

    // getters e setters
    public Long getId() { return id; }
    public LocalDate getDataAprovacao() { return dataAprovacao; }
    public double getValorCobrado() { return valorCobrado; }
    public double getValorImpostos() { return valorImpostos; }
    public void setId(Long id) { this.id = id; }
    public void setDataAprovacao(LocalDate dataAprovacao) { this.dataAprovacao = dataAprovacao; }
    public void setValorCobrado(double valorCobrado) { this.valorCobrado = valorCobrado; }
    public void setValorImpostos(double valorImpostos) { this.valorImpostos = valorImpostos; }
}

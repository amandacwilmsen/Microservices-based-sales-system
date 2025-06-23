package com.vendas.monolito.vendas_microservice.core.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.vendas.monolito.vendas_microservice.core.discount.DescontoStrategy;

public class Orcamento {
    private Long id;
    private String cliente;
    private LocalDate dataCriacao;
    private String estado;
    private String pais;
    private List<ItemOrcamento> itens;
    private boolean efetivado;
    private double totalItens;
    private double valorImpostos;
    private double desconto;
    private double totalFinal;

    public Orcamento(String cliente, LocalDate dataCriacao, String estado, String pais, List<ItemOrcamento> itens, boolean efetivado) {
        this.cliente = cliente;
        this.dataCriacao = dataCriacao;
        this.estado = estado;
        this.pais = pais;
        this.itens = itens;
        this.efetivado = efetivado;
    }

    public Orcamento(String cliente, String estado, String pais, List<ItemOrcamento> itens) {
        this.cliente = cliente;
        this.estado = estado;
        this.pais = pais;
        this.itens = itens;
    }

    public Orcamento(Long id, String cliente, LocalDate dataCriacao, String estado, String pais, List<ItemOrcamento> itens) {
        this.id = id;
        this.cliente = cliente;
        this.dataCriacao = dataCriacao;
        this.estado = estado;
        this.pais = pais;
        this.itens = List.copyOf(itens);
    }

    public Orcamento(Long id, String cliente, LocalDate dataCriacao, String estado, String pais, List<ItemOrcamento> itens, boolean efetivado, double totalItens, double valorImpostos, double desconto, double totalFinal) {
        this.id = id;
        this.cliente = cliente;
        this.dataCriacao = dataCriacao;
        this.estado = estado;
        this.pais = pais;
        this.itens = itens;
        this.efetivado = efetivado;
        this.totalItens = totalItens;
        this.valorImpostos = valorImpostos;
        this.desconto = desconto;
        this.totalFinal = totalFinal;
    }

    // NOVO CONSTRUTOR NECESSÁRIO
    public Orcamento(Long id, String cliente, LocalDate dataCriacao, String estado, String pais,
                    List<ItemOrcamento> itens, boolean efetivado, double totalItens,
                    double impostoEstadual, double impostoFederal, double desconto, double totalFinal) {
        this.id = id;
        this.cliente = cliente;
        this.dataCriacao = dataCriacao;
        this.estado = estado;
        this.pais = pais;
        this.itens = itens;
        this.efetivado = efetivado;
        this.totalItens = totalItens;
        this.valorImpostos = impostoEstadual + impostoFederal;
        this.desconto = desconto;
        this.totalFinal = totalFinal;
    }

    public BigDecimal getSubtotal() {
        return itens == null ? BigDecimal.ZERO : itens.stream()
                .map(ItemOrcamento::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public double getTotalItens() {
        return getSubtotal().doubleValue();
    }

    public void calcularTotais() {
        this.totalItens = getTotalItens();
        this.totalFinal = this.totalItens - this.desconto + this.valorImpostos;
    }

    public double calcularDesconto(DescontoStrategy descontoStrategy) {
        return descontoStrategy.calcularDesconto(this);
    }

    public ValoresImposto getValoresImposto() {
        return null; // Ajuste temporário para compilar
    }

    public double getValorTotal() {
        return totalFinal;
    }

    // GETTERS E SETTERS
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return this.cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataCriacao() {
        return this.dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return this.pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<ItemOrcamento> getItens() {
        return this.itens;
    }

    public void setItens(List<ItemOrcamento> itens) {
        this.itens = itens;
    }

    public boolean isEfetivado() {
        return this.efetivado;
    }

    public boolean getEfetivado() {
        return this.efetivado;
    }

    public void setEfetivado(boolean efetivado) {
        this.efetivado = efetivado;
    }
    
    public void setTotalItens(double totalItens) {
        this.totalItens = totalItens;
    }

    public double getValorImpostos() {
        return this.valorImpostos;
    }

    public void setValorImpostos(double valorImpostos) {
        this.valorImpostos = valorImpostos;
    }

    public double getDesconto() {
        return this.desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getTotalFinal() {
        return this.totalFinal;
    }

    public void setTotalFinal(double totalFinal) {
        this.totalFinal = totalFinal;
    }
    
    public LocalDate getDataEfetivacao() {
        return efetivado ? dataCriacao : null;
    }

    public double getImpostoEstadual() {
        return this.valorImpostos * 0.6; // 60% do total de impostos
    }

    public double getImpostoFederal() {
        return this.valorImpostos * 0.4; // 40% do total de impostos
    }
}
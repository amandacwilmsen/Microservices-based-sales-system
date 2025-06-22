package com.vendas.dto;

import java.time.LocalDate;

public class RegistroVendaDTO {
    private LocalDate data;
    private Double valorTotal;
    private Double impostos;

    public RegistroVendaDTO() {
    }

    public RegistroVendaDTO(LocalDate data, Double valorTotal, Double impostos) {
        this.data = data;
        this.valorTotal = valorTotal;
        this.impostos = impostos;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getImpostos() {
        return impostos;
    }

    public void setImpostos(Double impostos) {
        this.impostos = impostos;
    }
}

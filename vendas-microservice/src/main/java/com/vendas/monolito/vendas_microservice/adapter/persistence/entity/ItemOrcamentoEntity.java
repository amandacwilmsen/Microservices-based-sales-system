package com.vendas.monolito.vendas_microservice.adapter.persistence.entity;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_item_pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemOrcamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantidade;
    private BigDecimal precoUnitario;
    private Long produtoId;

    @ManyToOne
    @JoinColumn(name = "orcamento_id")
    private OrcamentoEntity orcamento;

    public ItemOrcamentoEntity ( Long produtoId, int quantidade, BigDecimal precoUnitario ) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }
}

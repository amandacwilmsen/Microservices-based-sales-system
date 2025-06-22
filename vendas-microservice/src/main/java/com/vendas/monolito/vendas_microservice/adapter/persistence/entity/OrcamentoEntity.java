package com.vendas.monolito.vendas_microservice.adapter.persistence.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_orcamento")
public class OrcamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;

    private String nomeCliente;

    private String estado;

    private String pais;

    @OneToMany(mappedBy = "orcamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemOrcamentoEntity> itens;

    @Column(nullable = false)
    private boolean efetivado;

    private Double totalItens;

    private Double impostoEstadual;

    private Double impostoFederal;

    private Double desconto;

    private Double totalFinal;

}
package com.vendas.monolito.vendas_microservice.core.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vendas.monolito.vendas_microservice.adapter.messaging.OrcamentoEventProducer;
import com.vendas.monolito.vendas_microservice.application.dto.event.RegistroOrcamentoEvent;
import com.vendas.monolito.vendas_microservice.core.model.Estoque;
import com.vendas.monolito.vendas_microservice.core.model.ItemOrcamento;
import com.vendas.monolito.vendas_microservice.core.model.Orcamento;
import com.vendas.monolito.vendas_microservice.core.repository.EstoqueInterface;
import com.vendas.monolito.vendas_microservice.core.repository.OrcamentoInterface;
import com.vendas.monolito.vendas_microservice.core.repository.ProdutoInterface;

public class EfetivarOrcamentoService {

    private static final Logger logger = LoggerFactory.getLogger(EfetivarOrcamentoService.class);

    private final OrcamentoInterface orcamentoInterface;
    private final EstoqueInterface estoqueInterface;
    private final OrcamentoEventProducer orcamentoEventProducer;

    public EfetivarOrcamentoService(OrcamentoInterface orcamentoInterface, EstoqueInterface estoqueInterface, ProdutoInterface produtoInterface, OrcamentoEventProducer orcamentoEventProducer) {
        this.orcamentoInterface = orcamentoInterface;
        this.estoqueInterface = estoqueInterface;
        this.orcamentoEventProducer = orcamentoEventProducer;
    }

    public void executar(Long id) {
        logger.info("Iniciando o processo de efetivação do orçamento com ID: {}", id);
        Orcamento orcamento = orcamentoInterface.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Orçamento não encontrado com id: " + id));

        if (orcamento.isEfetivado()) {
            logger.error("Tentativa de efetivar um orçamento já efetivado. ID: {}", id);
            throw new RuntimeException("Orcamento ja efetivado");
        }

        if (LocalDate.now().isAfter(orcamento.getDataCriacao().plusDays(21))) {
            logger.error("Orçamento expirado. ID: {}", id);
            throw new RuntimeException("Orcamento expirado");
        }

        for (ItemOrcamento item : orcamento.getItens()) {
            Estoque estoque = estoqueInterface.buscarPorProdutoId(item.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Estoque não encontrado para produto: " + item.getProdutoId()));
            if (estoque.getQuantidadeAtual() < item.getQuantidade()) {
                logger.error("Estoque insuficiente para o produto. Produto ID: {}, Quantidade solicitada: {}, Quantidade em estoque: {}", item.getProdutoId(), item.getQuantidade(), estoque.getQuantidadeAtual());
                throw new RuntimeException("Estoque insuficiente para produto: " + item.getProdutoId());
            }
            Estoque atualizado = new Estoque(
                estoque.getProdutoId(),
                estoque.getQuantidadeAtual() - item.getQuantidade(),
                estoque.getQuantidadeMinima(),
                estoque.getQuantidadeMaxima()
            );
            estoqueInterface.salvar(atualizado);
            logger.info("Estoque atualizado com sucesso. Produto ID: {}, Nova quantidade em estoque: {}", estoque.getProdutoId(), atualizado.getQuantidadeAtual());
        }

        orcamento.setEfetivado(true);
        orcamentoInterface.salvar(orcamento);
        logger.info("Orçamento efetivado com sucesso. ID: {}", id);

        BigDecimal valorFinal = BigDecimal.valueOf(orcamento.getTotalFinal());
        BigDecimal valorImpostos = BigDecimal.valueOf(orcamento.getValorImpostos());

        RegistroOrcamentoEvent event = new RegistroOrcamentoEvent(
                orcamento.getDataCriacao().atStartOfDay(),
                valorFinal,
                valorImpostos
        );
        orcamentoEventProducer.sendRegistroOrcamentoEvent(event);
        logger.info("Evento de registro de orçamento enviado para o ID: {}", id);
    }
}

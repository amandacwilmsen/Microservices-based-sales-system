package com.vendas.monolito.vendas_microservice.core.service;

import com.vendas.monolito.vendas_microservice.core.discount.DescontoComposto;
import com.vendas.monolito.vendas_microservice.core.discount.DescontoStrategy;
import com.vendas.monolito.vendas_microservice.core.discount.QuantidadeItemDesconto;
import com.vendas.monolito.vendas_microservice.core.discount.TotalItensDesconto;
import com.vendas.monolito.vendas_microservice.core.model.*;
import com.vendas.monolito.vendas_microservice.core.repository.OrcamentoInterface;
import com.vendas.monolito.vendas_microservice.core.repository.ProdutoInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class SolicitarOrcamentoService {

    private static final Logger logger = LoggerFactory.getLogger(SolicitarOrcamentoService.class);

    private final ProdutoInterface produtoRepository;
    private final OrcamentoInterface orcamentoRepository;
    private final ImpostoService impostoService;

    private static final Set<String> ESTADOS_ATENDIDOS = Set.of("RS", "SP", "PE");
    private static final String PAIS_PADRAO = "BR";

    public SolicitarOrcamentoService ( ProdutoInterface produtoRepository, OrcamentoInterface orcamentoRepository, ImpostoService impostoService ) {
        this.produtoRepository = produtoRepository;
        this.orcamentoRepository = orcamentoRepository;
        this.impostoService = impostoService;
    }

    public Orcamento executar( Orcamento orcamento){
        if (!PAIS_PADRAO.equalsIgnoreCase(orcamento.getPais()) || !ESTADOS_ATENDIDOS.contains(orcamento.getEstado().toUpperCase())) {
            throw new RuntimeException("Região não atendida.");
        }
        List<ItemOrcamento> itens = new ArrayList<>();
        for (ItemOrcamento item : orcamento.getItens()) {
            Produto produto = produtoRepository.buscarPorId(item.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + item.getProdutoId()));
            itens.add(new ItemOrcamento(produto.getId(), item.getQuantidade(), produto.getPrecoUnitario()));
        }

        orcamento.setItens(itens);

        ValoresImposto valoresImposto = impostoService.calcularImposto(orcamento);
        double valorImpostos = valoresImposto.valorTotalImposto().doubleValue();

        DescontoStrategy descontoComposto = new DescontoComposto(Arrays.asList(
            new QuantidadeItemDesconto(),
            new TotalItensDesconto()
        ));

        CalcularDescontoService calcularDescontoService = new CalcularDescontoService(descontoComposto);
        double valorDesconto = calcularDescontoService.executar(orcamento);
        double subTotal = orcamento.getSubtotal().doubleValue();
        double valorFinal = subTotal + valorImpostos - valorDesconto;

        orcamento.setTotalItens(subTotal);
        orcamento.setValorImpostos(valorImpostos);
        orcamento.setDesconto(valorDesconto);
        orcamento.setTotalFinal(valorFinal);

        Orcamento orcamentoSalvo = orcamentoRepository.salvar(orcamento);

        logger.info("Orçamento solicitado com sucesso. Cliente: {}, Data: {}, Estado: {}, País: {}, Itens: {}, Total de Itens: {}, Valor Impostos: {}, Valor Desconto: {}, Valor Final: {}",
            orcamento.getCliente(),
            LocalDate.now(),
            orcamento.getEstado(),
            orcamento.getPais(),
            itens,
            orcamento.getTotalItens(),
            valorImpostos,
            valorDesconto,
            valorFinal
        );

        return orcamentoSalvo;
    }

}
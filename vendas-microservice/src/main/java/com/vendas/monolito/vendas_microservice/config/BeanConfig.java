package com.vendas.monolito.vendas_microservice.config;

import com.vendas.monolito.vendas_microservice.application.usecases.*;
import com.vendas.monolito.vendas_microservice.core.repository.EstoqueInterface;
import com.vendas.monolito.vendas_microservice.core.repository.OrcamentoInterface;
import com.vendas.monolito.vendas_microservice.core.repository.ProdutoInterface;
import com.vendas.monolito.vendas_microservice.core.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    // Camada de Serviço (Core)
    @Bean
    public EstoqueService estoqueService(EstoqueInterface estoqueRepository) {
        return new EstoqueService(estoqueRepository);
    }

    @Bean
    public ListarOrcamentosService listarOrcamentosService(OrcamentoInterface orcamentoRepository) {
        return new ListarOrcamentosService(orcamentoRepository);
    }

    @Bean
    public EfetivarOrcamentoService efetivarOrcamentoService(OrcamentoInterface orcamentoInterface, EstoqueInterface estoqueInterface, ProdutoInterface produtoInterface) {
        return new EfetivarOrcamentoService(orcamentoInterface, estoqueInterface, produtoInterface);
    }

    @Bean
    public ListarProdutoService listarProdutoService(ProdutoInterface produtoInterface) {
        return new ListarProdutoService(produtoInterface);
    }

    @Bean
    public SolicitarOrcamentoService solicitarOrcamentoService(ProdutoInterface produtoRepository, OrcamentoInterface orcamentoRepository, ImpostoService impostoService) {
        return new SolicitarOrcamentoService(produtoRepository, orcamentoRepository, impostoService);
    }

    // Camada de Casos de Uso (Application)
    @Bean
    public ConsultarEstoqueUseCase consultarEstoqueUseCase(EstoqueService estoqueService) {
        return new ConsultarEstoqueUseCase(estoqueService);
    }

    @Bean
    public EfetivarOrcamentoUseCase efetivarOrcamentoUseCase(EfetivarOrcamentoService efetivarOrcamentoService) {
        return new EfetivarOrcamentoUseCase(efetivarOrcamentoService);
    }

    @Bean
    public EntradaEstoqueUseCase entradaEstoqueUseCase(EstoqueService estoqueService) {
        return new EntradaEstoqueUseCase(estoqueService);
    }

    @Bean
    public ListarEstoqueUseCase listarEstoqueUseCase(EstoqueInterface estoqueRepository) {
        return new ListarEstoqueUseCase(estoqueRepository);
    }

    @Bean
    public ListarOrcamentosEfetivadosUseCase listarOrcamentosEfetivadosUseCase(ListarOrcamentosService listarOrcamentosService) {
        return new ListarOrcamentosEfetivadosUseCase(listarOrcamentosService);
    }

    @Bean
    public ListarProdutoUseCase listarProdutoUseCase(ListarProdutoService listarProdutoService) {
        return new ListarProdutoUseCase(listarProdutoService);
    }

    @Bean
    public SolicitarOrcamentoUseCase solicitarOrcamentoUseCase(SolicitarOrcamentoService solicitarOrcamentoService) {
        return new SolicitarOrcamentoUseCase(solicitarOrcamentoService);
    }
}

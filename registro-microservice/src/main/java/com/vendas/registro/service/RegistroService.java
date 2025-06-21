package com.vendas.registro.service;

import com.vendas.registro.dto.ConsultaPeriodoRequest;
import com.vendas.registro.dto.RelatorioMensalResponse;
import com.vendas.registro.entity.VendaAprovada;
import com.vendas.registro.repository.VendaAprovadaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Service
@Transactional
public class RegistroService {

    private static final Logger logger = LoggerFactory.getLogger(RegistroService.class);

    @Autowired
    private VendaAprovadaRepository repository;

    @Transactional(readOnly = true)
    public RelatorioMensalResponse gerarRelatorioMensal(ConsultaPeriodoRequest request) {
        logger.info("Gerando relatório mensal para {}/{}", request.mes(), request.ano());

        BigDecimal totalVendido = repository.findTotalVendidoPorMes(request.ano(), request.mes());
        BigDecimal totalImpostos = repository.findTotalImpostosPorMes(request.ano(), request.mes());
        long quantidadeVendas = repository.countVendasPorMes(request.ano(), request.mes());

        String mesNome = Month.of(request.mes()).name();

        RelatorioMensalResponse relatorio = new RelatorioMensalResponse(
            request.ano(),
            request.mes(),
            mesNome,
            totalVendido != null ? totalVendido : BigDecimal.ZERO,
            totalImpostos != null ? totalImpostos : BigDecimal.ZERO,
            (int) quantidadeVendas,
            LocalDateTime.now()
        );

        logger.info("Relatório gerado: Total Vendido: {}, Total Impostos: {}, Qtd Vendas: {}", 
                   relatorio.totalVendido(), relatorio.totalImpostos(), relatorio.quantidadeVendas());

        return relatorio;
    }

    @Transactional(readOnly = true)
    public List<VendaAprovada> buscarVendasPorMes(int ano, int mes) {
        logger.info("Buscando vendas detalhadas para {}/{}", mes, ano);
        return repository.findByDataVendaBetweenOrderByDataVendaDesc(
            java.time.LocalDate.of(ano, mes, 1),
            java.time.LocalDate.of(ano, mes, java.time.YearMonth.of(ano, mes).lengthOfMonth())
        );
    }

    @Transactional(readOnly = true)
    public List<VendaAprovada> buscarTodasVendas() {
        logger.info("Buscando todas as vendas registradas");
        return repository.findAll();
    }

    // Método para simular inserção de dados (teste)
    public VendaAprovada criarVendaTeste(String numeroOrcamento, BigDecimal valorCobrado, BigDecimal valorImpostos) {
        VendaAprovada venda = new VendaAprovada(
            numeroOrcamento, 
            java.time.LocalDate.now(), 
            valorCobrado, 
            valorImpostos
        );
        return repository.save(venda);
    }
}

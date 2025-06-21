package com.vendas.registro.controller;

import com.vendas.registro.dto.ConsultaPeriodoRequest;
import com.vendas.registro.dto.RelatorioMensalResponse;
import com.vendas.registro.entity.VendaAprovada;
import com.vendas.registro.service.RegistroService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/registro")
public class RegistroController {

    private static final Logger logger = LoggerFactory.getLogger(RegistroController.class);

    @Autowired
    private RegistroService registroService;

    // ENDPOINT DA RECEITA FEDERAL (REQUISITO PRINCIPAL)
    @GetMapping("/relatorio-mensal/{ano}/{mes}")
    public ResponseEntity<RelatorioMensalResponse> gerarRelatorioMensalPath(
            @PathVariable Integer ano, 
            @PathVariable Integer mes) {
        
        logger.info("Requisição de relatório mensal via path: {}/{}", ano, mes);
        
        ConsultaPeriodoRequest request = new ConsultaPeriodoRequest(ano, mes);
        RelatorioMensalResponse relatorio = registroService.gerarRelatorioMensal(request);
        return ResponseEntity.ok(relatorio);
    }

    @PostMapping("/relatorio-mensal")
    public ResponseEntity<RelatorioMensalResponse> gerarRelatorioMensal(
            @Valid @RequestBody ConsultaPeriodoRequest request) {
        
        logger.info("Requisição de relatório mensal recebida: {}", request);
        
        RelatorioMensalResponse relatorio = registroService.gerarRelatorioMensal(request);
        return ResponseEntity.ok(relatorio);
    }

    @GetMapping("/vendas/{ano}/{mes}")
    public ResponseEntity<List<VendaAprovada>> buscarVendasDetalhadas(
            @PathVariable Integer ano,
            @PathVariable Integer mes) {
        
        logger.info("Buscando vendas detalhadas para {}/{}", mes, ano);
        
        List<VendaAprovada> vendas = registroService.buscarVendasPorMes(ano, mes);
        return ResponseEntity.ok(vendas);
    }

    @GetMapping("/vendas")
    public ResponseEntity<List<VendaAprovada>> buscarTodasVendas() {
        logger.info("Buscando todas as vendas registradas");
        
        List<VendaAprovada> vendas = registroService.buscarTodasVendas();
        return ResponseEntity.ok(vendas);
    }

    // Endpoint para criar venda de teste
    @PostMapping("/vendas/teste")
    public ResponseEntity<VendaAprovada> criarVendaTeste(
            @RequestParam String numeroOrcamento,
            @RequestParam BigDecimal valorCobrado,
            @RequestParam BigDecimal valorImpostos) {
        
        VendaAprovada venda = registroService.criarVendaTeste(numeroOrcamento, valorCobrado, valorImpostos);
        return ResponseEntity.ok(venda);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of(
            "status", "UP",
            "service", "registro-microservice",
            "timestamp", java.time.LocalDateTime.now().toString()
        ));
    }
}

package com.vendas.monolito.vendas_microservice.adapter.controller;

import com.vendas.monolito.vendas_microservice.application.dto.response.OrcamentoResponse;
import com.vendas.monolito.vendas_microservice.application.usecases.ListarOrcamentosEfetivadosUseCase;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/orcamentos/efetivados")
public class OrcamentoEfetivadoController {
    private static final Logger logger = LoggerFactory.getLogger(OrcamentoEfetivadoController.class);

    private final ListarOrcamentosEfetivadosUseCase listarOrcamentosEfetivadosUseCase;

    @GetMapping
    public ResponseEntity<List<OrcamentoResponse>> listarEfetivadosPorPeriodo(
            @RequestParam("dataInicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam("dataFinal") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {
        logger.info("Listando orçamentos efetivados do período: {} a {}", dataInicial, dataFinal);
        List<OrcamentoResponse> efetivados = listarOrcamentosEfetivadosUseCase.executar(dataInicial, dataFinal);
        return ResponseEntity.ok(efetivados);
    }
}

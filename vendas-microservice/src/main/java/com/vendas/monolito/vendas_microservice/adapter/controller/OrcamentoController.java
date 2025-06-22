package com.vendas.monolito.vendas_microservice.adapter.controller;

import com.vendas.monolito.vendas_microservice.application.dto.request.OrcamentoRequest;
import com.vendas.monolito.vendas_microservice.application.dto.response.OrcamentoResponse;
import com.vendas.monolito.vendas_microservice.application.usecases.EfetivarOrcamentoUseCase;
import com.vendas.monolito.vendas_microservice.application.usecases.SolicitarOrcamentoUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/orcamentos")
@AllArgsConstructor
public class OrcamentoController {

    private static final Logger logger = LoggerFactory.getLogger(OrcamentoController.class);

    private final SolicitarOrcamentoUseCase solicitarOrcamentoUseCase;
    private final EfetivarOrcamentoUseCase efetivarUseCase;

    @PostMapping
    public ResponseEntity<OrcamentoResponse> solicitar(@RequestBody OrcamentoRequest request) {
        logger.info("Recebida solicitação para solicitar orçamento.");
        OrcamentoResponse response = solicitarOrcamentoUseCase.executar(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/efetivar")
    public ResponseEntity<?> efetivar(@PathVariable("id") Long id) {
        logger.info("Recebida solicitação para efetivar orçamento com ID: {}", id);
        efetivarUseCase.executar(id);
        return ResponseEntity.ok().build();
    }
}

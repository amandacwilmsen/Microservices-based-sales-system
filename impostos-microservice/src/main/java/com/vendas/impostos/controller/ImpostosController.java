package com.vendas.impostos.controller;

import com.vendas.impostos.dto.CalculoImpostoRequest;
import com.vendas.impostos.dto.CalculoImpostoResponse;
import com.vendas.impostos.service.ImpostosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/impostos")
public class ImpostosController {

    @Autowired
    private ImpostosService impostosService;

    @PostMapping("/calcular")
    public ResponseEntity<CalculoImpostoResponse> calcularImpostos(
            @Valid @RequestBody CalculoImpostoRequest request) {
        
        CalculoImpostoResponse response = impostosService.calcularImpostos(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of(
            "status", "UP",
            "service", "impostos-microservice",
            "timestamp", java.time.LocalDateTime.now().toString()
        ));
    }

    @GetMapping("/tipos-produto")
    public ResponseEntity<CalculoImpostoRequest.TipoProduto[]> getTiposProduto() {
        return ResponseEntity.ok(CalculoImpostoRequest.TipoProduto.values());
    }
}

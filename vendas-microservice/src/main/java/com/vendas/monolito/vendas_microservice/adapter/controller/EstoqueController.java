package com.vendas.monolito.vendas_microservice.adapter.controller;

import com.vendas.monolito.vendas_microservice.application.dto.request.EstoqueRequest;
import com.vendas.monolito.vendas_microservice.application.usecases.ConsultarEstoqueUseCase;
import com.vendas.monolito.vendas_microservice.application.usecases.EntradaEstoqueUseCase;
import com.vendas.monolito.vendas_microservice.application.usecases.ListarEstoqueUseCase;
import com.vendas.monolito.vendas_microservice.core.model.Estoque;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/estoque")
public class EstoqueController {
    private static final Logger logger = LoggerFactory.getLogger(EstoqueController.class);
    private final EntradaEstoqueUseCase entradaEstoqueUseCase;
    private final ListarEstoqueUseCase listarEstoqueUseCase;
    private final ConsultarEstoqueUseCase consultarEstoqueUseCase;

    @PostMapping("/entrada")
    public ResponseEntity<?> entradaProduto(@RequestBody EstoqueRequest entrada) {
        entradaEstoqueUseCase.executar(entrada);
        logger.info("Produto adicionado ao estoque: {}", entrada.produtoId());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Estoque>> listarEstoque() {
        logger.info("Listando todos os produtos do estoque.");
        return ResponseEntity.ok(listarEstoqueUseCase.executar());
    }

    @PostMapping("/consulta")
    public ResponseEntity<Map<Long, Integer>> consultarEstoque(@RequestBody List<Long> ids) {
        logger.info("Consultando estoque para os produtos: {}", ids);
        Map<Long, Integer> result = consultarEstoqueUseCase.executar(ids);
        return ResponseEntity.ok(result);
    }
}

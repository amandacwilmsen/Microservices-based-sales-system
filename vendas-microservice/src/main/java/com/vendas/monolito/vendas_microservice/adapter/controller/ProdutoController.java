package com.vendas.monolito.vendas_microservice.adapter.controller;

import java.util.List;

import com.vendas.monolito.vendas_microservice.application.dto.response.ProdutoResponse;
import com.vendas.monolito.vendas_microservice.application.usecases.ListarProdutoUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private ListarProdutoUseCase listarProdutosUseCase;
    private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> listarTodos() {
        logger.info("Recebida solicitação para listar produtos.");
        return ResponseEntity.ok(listarProdutosUseCase.listarProdutos());
    }

}

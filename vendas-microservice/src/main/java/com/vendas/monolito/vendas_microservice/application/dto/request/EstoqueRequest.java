package com.vendas.monolito.vendas_microservice.application.dto.request;

public record EstoqueRequest(Long produtoId, int quantidadeAtual, int quantidadeMinima, int quantidadeMaxima) {
}


package com.vendas.monolito.vendas_microservice.adapter.client;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.vendas.monolito.vendas_microservice.application.dto.request.CalculoImpostoRequest;
import com.vendas.monolito.vendas_microservice.application.dto.response.CalculoImpostoResponse;
import com.vendas.monolito.vendas_microservice.core.model.Orcamento;
import com.vendas.monolito.vendas_microservice.core.model.ValoresImposto;
import com.vendas.monolito.vendas_microservice.core.service.ImpostoService;

@Primary
@Service
public class ImpostoServiceWebClient implements ImpostoService {

    private final WebClient webClient;

    public ImpostoServiceWebClient() {
        this.webClient = WebClient.builder()
                .baseUrl("http://impostos-microservice:8082") 
                .build();
    }

    @Override
    public ValoresImposto calcularImposto(Orcamento orcamento) {
        CalculoImpostoRequest request = new CalculoImpostoRequest(
                orcamento.getSubtotal(),
                orcamento.getEstado()
        );

        CalculoImpostoResponse response = webClient.post()
                .uri("/api/impostos/calcular")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(CalculoImpostoResponse.class)
                .block();

        if (response == null) {
            throw new RuntimeException("Falha na comunicação REST com o microserviço de impostos.");
        }

        return new ValoresImposto(response.valorImpostos());
    }
}

package com.vendas.monolito.vendas_microservice.adapter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class HealthController {
    private static final Logger logger = LoggerFactory.getLogger(HealthController.class);

    @GetMapping("/health")
    public String health() {
        logger.info("Recebida solicitação para verificar saúde do sistema.");
        return "OK";
    }
}
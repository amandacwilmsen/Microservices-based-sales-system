package com.vendas.service;

import com.vendas.config.RabbitMQConfig;
import com.vendas.dto.RegistroVendaDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OrcamentoService {

    private final RabbitTemplate rabbitTemplate;

    public OrcamentoService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void aprovarOrcamento(Double valor, Double impostos) {
        RegistroVendaDTO dto = new RegistroVendaDTO(LocalDate.now(), valor, impostos);
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, dto);
        System.out.println("Orçamento aprovado e enviado para fila.");
    }
}

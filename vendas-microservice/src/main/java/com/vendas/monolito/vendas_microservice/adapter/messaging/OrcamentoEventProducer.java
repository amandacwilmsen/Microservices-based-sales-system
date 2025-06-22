package com.vendas.monolito.vendas_microservice.adapter.messaging;

import com.vendas.monolito.vendas_microservice.application.dto.event.RegistroOrcamentoEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OrcamentoEventProducer {

    private final RabbitTemplate rabbitTemplate;
    private final String orcamentoEfetivadoQueueName;
    private final String registroOrcamentoQueueName;

    public OrcamentoEventProducer(RabbitTemplate rabbitTemplate,
                                  @Value("${app.rabbitmq.orcamento-efetivado-queue:orcamento.efetivado.v1}") String orcamentoEfetivadoQueueName,
                                  @Value("${app.rabbitmq.registro-orcamento-queue:registro.orcamento.v1}") String registroOrcamentoQueueName) {
        this.rabbitTemplate = rabbitTemplate;
        this.orcamentoEfetivadoQueueName = orcamentoEfetivadoQueueName;
        this.registroOrcamentoQueueName = registroOrcamentoQueueName;
    }

    public void sendOrcamentoEfetivadoEvent(Object evento) {
        rabbitTemplate.convertAndSend(orcamentoEfetivadoQueueName, evento);
    }

    public void sendRegistroOrcamentoEvent(RegistroOrcamentoEvent evento) {
        rabbitTemplate.convertAndSend(registroOrcamentoQueueName, evento);
    }
}

package com.vendas.registro.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    private static final String ORCAMENTO_QUEUE_NAME = "orcamento.efetivado.v1";
    private static final String ORCAMENTO_EXCHANGE_NAME = "orcamento.exchange";

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public DirectExchange orcamentoExchange() {
        return new DirectExchange(ORCAMENTO_EXCHANGE_NAME, true, false);
    }
    
    @Bean
    public Queue orcamentoEfetivadoQueue() {
        return new Queue(ORCAMENTO_QUEUE_NAME, true, false, false);
    }
    
    @Bean
    public Binding orcamentoBinding(Queue orcamentoEfetivadoQueue, DirectExchange orcamentoExchange) {
        return BindingBuilder.bind(orcamentoEfetivadoQueue)
                .to(orcamentoExchange)
                .with(ORCAMENTO_QUEUE_NAME);
    }
}

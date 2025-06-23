
package com.vendas.registro.service;

import com.vendas.registro.entity.VendaAprovada;
import com.vendas.registro.repository.VendaAprovadaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class RegistroOrcamentoEventListener {

    private static final Logger logger = LoggerFactory.getLogger(RegistroOrcamentoEventListener.class);

    @Autowired
    private VendaAprovadaRepository vendaAprovadaRepository;

    @RabbitListener(queues = "registro.orcamento.v1")
    public void receberRegistroOrcamento(RegistroOrcamentoEvent evento) {
        logger.info("Evento de registro recebido: {}", evento);
        
        try {
            VendaAprovada venda = new VendaAprovada();
            venda.setNumeroOrcamento("ORG-" + System.currentTimeMillis());
            venda.setDataVenda(evento.data().toLocalDate());
            venda.setValorCobrado(evento.valorFinal());
            venda.setValorImpostos(evento.valorImpostos());
            venda.setDataRegistro(LocalDateTime.now());
            venda.setObservacoes("Registro automático via evento");

            vendaAprovadaRepository.save(venda);
            logger.info("Venda registrada com sucesso: ID {}", venda.getId());
            
        } catch (Exception e) {
            logger.error("Erro ao processar evento de registro: {}", e.getMessage(), e);
        }
    }

    public record RegistroOrcamentoEvent(
        LocalDateTime data,
        BigDecimal valorFinal,
        BigDecimal valorImpostos
    ) {}
}

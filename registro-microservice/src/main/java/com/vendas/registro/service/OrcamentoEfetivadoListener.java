package com.vendas.registro.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vendas.registro.domain.OrcamentoAprovadoHistorico;
import com.vendas.registro.repository.OrcamentoAprovadoHistoricoRepository;

@Component
public class OrcamentoEfetivadoListener {

    @Autowired
    private OrcamentoAprovadoHistoricoRepository historicoRepository;

    @RabbitListener(queues = "orcamento.efetivado.v1")
    public void receiveOrcamentoEfetivado(OrcamentoEfetivadoEvent event) {
        OrcamentoAprovadoHistorico historico = new OrcamentoAprovadoHistorico(
            event.dataEfetivacao(),
            event.valorTotal().toBigInteger().doubleValue(),
            event.valorImpostos().toBigInteger().doubleValue()
        );
        historicoRepository.save(historico);
    }
}

package com.vendas.impostos.service;

import com.vendas.impostos.dto.CalculoImpostoRequest;
import com.vendas.impostos.dto.CalculoImpostoResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

@Service
public class ImpostosService {

    @Value("${server.port}")
    private String serverPort;

    public CalculoImpostoResponse calcularImpostos(CalculoImpostoRequest request) {
        
        var valorBase = request.valorBase();
        var aliquotas = calcularAliquotas(request.tipoProduto());
        
        // Calcula cada imposto separadamente
        var icms = valorBase.multiply(aliquotas.icms()).setScale(2, RoundingMode.HALF_UP);
        var ipi = valorBase.multiply(aliquotas.ipi()).setScale(2, RoundingMode.HALF_UP);
        var pis = valorBase.multiply(aliquotas.pis()).setScale(2, RoundingMode.HALF_UP);
        var cofins = valorBase.multiply(aliquotas.cofins()).setScale(2, RoundingMode.HALF_UP);
        
        // Soma total dos impostos
        var totalImpostos = icms.add(ipi).add(pis).add(cofins);
        var valorTotal = valorBase.add(totalImpostos);
        var aliquotaTotal = totalImpostos.divide(valorBase, 4, RoundingMode.HALF_UP);

        var detalhes = new CalculoImpostoResponse.DetalheImpostos(
            icms, ipi, pis, cofins, aliquotaTotal
        );

        return new CalculoImpostoResponse(
            valorBase,
            totalImpostos,
            valorTotal,
            LocalDateTime.now(),
            getInstanciaServico(),
            detalhes
        );
    }

    private AliquotasImpostos calcularAliquotas(CalculoImpostoRequest.TipoProduto tipo) {
        return switch (tipo) {
            case ELETRÔNICOS -> new AliquotasImpostos(
                new BigDecimal("0.18"), // ICMS 18%
                new BigDecimal("0.10"), // IPI 10%
                new BigDecimal("0.0165"), // PIS 1.65%
                new BigDecimal("0.076") // COFINS 7.6%
            );
            case ROUPAS -> new AliquotasImpostos(
                new BigDecimal("0.18"), // ICMS 18%
                new BigDecimal("0.05"), // IPI 5%
                new BigDecimal("0.0165"), // PIS 1.65%
                new BigDecimal("0.076") // COFINS 7.6%
            );
            case LIVROS -> new AliquotasImpostos(
                new BigDecimal("0.00"), // ICMS isento
                new BigDecimal("0.00"), // IPI isento
                new BigDecimal("0.0165"), // PIS 1.65%
                new BigDecimal("0.076") // COFINS 7.6%
            );
            case ALIMENTOS -> new AliquotasImpostos(
                new BigDecimal("0.07"), // ICMS reduzido 7%
                new BigDecimal("0.00"), // IPI isento
                new BigDecimal("0.0165"), // PIS 1.65%
                new BigDecimal("0.076") // COFINS 7.6%
            );
            case MEDICAMENTOS -> new AliquotasImpostos(
                new BigDecimal("0.00"), // ICMS isento
                new BigDecimal("0.00"), // IPI isento
                new BigDecimal("0.0165"), // PIS 1.65%
                new BigDecimal("0.076") // COFINS 7.6%
            );
            default -> new AliquotasImpostos(
                new BigDecimal("0.18"), // ICMS 18%
                new BigDecimal("0.08"), // IPI 8%
                new BigDecimal("0.0165"), // PIS 1.65%
                new BigDecimal("0.076") // COFINS 7.6%
            );
        };
    }

    private String getInstanciaServico() {
        try {
            String hostname = InetAddress.getLocalHost().getHostName();
            return String.format("%s:%s", hostname, serverPort);
        } catch (UnknownHostException e) {
            return String.format("unknown:%s", serverPort);
        }
    }

    private record AliquotasImpostos(
        BigDecimal icms,
        BigDecimal ipi,
        BigDecimal pis,
        BigDecimal cofins
    ) {}
}

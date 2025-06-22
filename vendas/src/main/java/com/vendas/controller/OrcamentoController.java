package com.vendas.controller;

import com.vendas.service.OrcamentoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orcamentos")
public class OrcamentoController {

    private final OrcamentoService orcamentoService;

    public OrcamentoController(OrcamentoService orcamentoService) {
        this.orcamentoService = orcamentoService;
    }

    @PostMapping("/aprovar")
    public String aprovarOrcamento(@RequestParam Double valor, @RequestParam Double impostos) {
        orcamentoService.aprovarOrcamento(valor, impostos);
        return "Orçamento aprovado!";
    }
}

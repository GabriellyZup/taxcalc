package com.taxcalc.controller;

import com.taxcalc.dto.CalculationRequestDTO;
import com.taxcalc.dto.CalculationResponseDTO;
import com.taxcalc.service.TaxCalculationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@RestController
@RequestMapping("/calculo")
public class TaxCalculationController {
    private final TaxCalculationService taxCalculationService;

    public TaxCalculationController(TaxCalculationService taxCalculationService) {
        this.taxCalculationService = taxCalculationService;
    }

    @PostMapping
    public CalculationResponseDTO calculateTax(
            @RequestBody CalculationRequestDTO request,
            @RequestHeader("X-User-Role") String role // Adicione validação de role se necessário
    ) {
        // Validação de role temporária (exemplo)
        if (!"ADMIN".equals(role) ) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso restrito a ADMIN");
        }

        BigDecimal taxAmount = taxCalculationService.calculateTax(
                request.getTaxTypeId(),
                request.getBaseValue()
        );

        return taxCalculationService.buildCalculationResponse(
                request.getTaxTypeId(),
                request.getBaseValue(),
                taxAmount
        );
    }
}
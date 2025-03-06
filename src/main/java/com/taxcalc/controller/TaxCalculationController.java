package com.taxcalc.controller;

import com.taxcalc.service.TaxCalculationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculo")
public class TaxCalculationController {
    private final TaxCalculationService taxCalculationService;

    public TaxCalculationController(TaxCalculationService taxCalculationService) {
        this.taxCalculationService = taxCalculationService;
    }

    @PostMapping
    public CalculationResult calculateTax(
            @RequestBody CalculationRequest request
    ) {
        double taxAmount = taxCalculationService.calculateTax(request.getTaxTypeId(), request.getBaseValue());
        return new CalculationResult(request.getTaxTypeId(), request.getBaseValue(), taxAmount);
    }

    // DTOs internos
    private static class CalculationRequest {
        private Long taxTypeId;
        private double baseValue;
        // Getters/Setters
    }

    private static class CalculationResult {
        private Long taxTypeId;
        private double baseValue;
        private double taxAmount;
        // Getters/Setters
    }
}
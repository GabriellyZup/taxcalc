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

    private static class CalculationRequest {
        private Long taxTypeId;
        private double baseValue;

        public Long getTaxTypeId() {
            return taxTypeId; // retorna o campo
        }

        public void setTaxTypeId(Long taxTypeId) {
            this.taxTypeId = taxTypeId;
        }

        public double getBaseValue() {
            return baseValue;
        }

        public void setBaseValue(double baseValue) {
            this.baseValue = baseValue;
        }
    }

    private static class CalculationResult {
        private final Long taxTypeId; // ← Usando final para imutabilidade
        private final double baseValue;
        private final double taxAmount;

        public CalculationResult(Long taxTypeId, double baseValue, double taxAmount) {
            this.taxTypeId = taxTypeId;
            this.baseValue = baseValue;
            this.taxAmount = taxAmount;
        }

        public Long getTaxTypeId() {
            return taxTypeId;
        }

        public double getBaseValue() {
            return baseValue;
        }

        public double getTaxAmount() {
            return taxAmount;
        }
    }
}
package com.taxcalc.dto;

import jakarta.validation.constraints.*;

public class TaxTypeRequest {
    @NotBlank(message = "O nome é obrigatório")
    private String name;

    private String description;

    @DecimalMin(value = "0.0", message = "A alíquota não pode ser negativa")
    @DecimalMax(value = "100.0", message = "A alíquota não pode ultrapassar 100%")
    private Double taxRate;

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
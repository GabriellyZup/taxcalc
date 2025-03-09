package com.taxcalc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CalculationResponseDTO {
    @JsonProperty("tipoImposto")
    private String taxTypeName;

    @JsonProperty("valorBase")
    private Double baseValue;

    @JsonProperty("aliquota")
    private Double taxRate;

    @JsonProperty("valorImposto")
    private Double taxAmount;

    public CalculationResponseDTO(String taxTypeName, Double baseValue, Double taxRate, Double taxAmount) {
        this.taxTypeName = taxTypeName;
        this.baseValue = baseValue;
        this.taxRate = taxRate;
        this.taxAmount = taxAmount;
    }

    public String getTaxTypeName() {
        return taxTypeName;
    }

    public Double getBaseValue() {
        return baseValue;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }
}
package com.taxcalc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class CalculationResponseDTO {
    @JsonProperty("tipoImposto")
    private String taxTypeName;

    @JsonProperty("valorBase")
    private BigDecimal baseValue;

    @JsonProperty("aliquota")
    private BigDecimal taxRate;

    @JsonProperty("valorImposto")
    private BigDecimal taxAmount;

    @JsonProperty("valorTotal")
    private BigDecimal totalAmount; // New field

    // Updated constructor with all fields
    public CalculationResponseDTO(
            String taxTypeName,
            BigDecimal baseValue,
            BigDecimal taxRate,
            BigDecimal taxAmount,
            BigDecimal totalAmount)

    {

        this.taxTypeName = taxTypeName;
        this.baseValue = baseValue;
        this.taxRate = taxRate;
        this.taxAmount = taxAmount;
        this.totalAmount = totalAmount;
    }

    public String getTaxTypeName() {
        return taxTypeName;
    }

    public BigDecimal getBaseValue() {
        return baseValue;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
}
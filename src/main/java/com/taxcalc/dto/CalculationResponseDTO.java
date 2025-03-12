package com.taxcalc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(name = "CalculationResponse", description = "DTO for tax calculation response")
public class CalculationResponseDTO {

    @JsonProperty("tipoImposto")
    @Schema(description = "Nome do imposto", example = "ICMS")
    private String taxTypeName;

    @JsonProperty("valorBase")
    @Schema(description = "Valor base o calculo", example = "1000.0")
    private BigDecimal baseValue;

    @JsonProperty("aliquota")
    @Schema(description = "Taxa de imposto devido", example = "18.0")
    private BigDecimal taxRate;

    @JsonProperty("valorImposto")
    @Schema(description = "Calculo do valor do imposto", example = "180.0")
    private BigDecimal taxAmount;

    @JsonProperty("valorTotal")
    @Schema(description = "Total  (base + imposto)", example = "1180.0")
    private BigDecimal totalAmount;

    public CalculationResponseDTO(
            String taxTypeName,
            BigDecimal baseValue,
            BigDecimal taxRate,
            BigDecimal taxAmount,
            BigDecimal totalAmount
    ) {
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
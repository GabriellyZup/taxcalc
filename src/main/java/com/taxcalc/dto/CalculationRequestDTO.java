package com.taxcalc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class CalculationRequestDTO {
    @NotNull(message = "O ID do tipo de imposto é obrigatório")
    @Positive(message = "O ID do tipo de imposto deve ser positivo")
    @JsonProperty("tipoImpostoId")
    private Long taxTypeId;

    @NotNull(message = "O valor base é obrigatório")
    @PositiveOrZero(message = "O valor base não pode ser negativo")
    @JsonProperty("valorBase")
    private Double baseValue;

    public Long getTaxTypeId() {
        return taxTypeId;
    }

    public void setTaxTypeId(Long taxTypeId) {
        this.taxTypeId = taxTypeId;
    }

    public BigDecimal getBaseValue() {
        return BigDecimal.valueOf(baseValue);
    }

    public void setBaseValue(Double baseValue) {
        this.baseValue = baseValue;
    }
}
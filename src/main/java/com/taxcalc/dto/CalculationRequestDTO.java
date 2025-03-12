package com.taxcalc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Schema(name = "CalculationRequest", description = "DTO for tax calculation request")
public class CalculationRequestDTO {

    @NotNull(message = "O ID do tipo de imposto é obrigatório")
    @Positive(message = "O ID do tipo de imposto deve ser positivo")
    @JsonProperty("tipoImpostoId")
    @Schema(
            description = "ID of the tax type",
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Long taxTypeId;

    @NotNull(message = "O valor base é obrigatório")
    @PositiveOrZero(message = "O valor base não pode ser negativo")
    @JsonProperty("valorBase")
    @Schema(
            description = "Base value for calculation",
            example = "1000.0",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
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
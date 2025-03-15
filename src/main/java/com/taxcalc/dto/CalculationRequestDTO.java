package com.taxcalc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Schema(name = "CalculationRequest", description = "DTO request para o cálculo do imposto")
public class CalculationRequestDTO {

    @NotNull(message = "O ID do tipo de imposto é obrigatório")
    @Positive(message = "O ID do tipo de imposto deve ser positivo")
    @JsonProperty("tipoImpostoId")
    @Schema(
            description = "ID of the tax type",
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Long tipoImpostoId;

    @JsonProperty("valorBase")
    @Schema(
            description = "Valor base para calculo",
            example = "1000.0",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private BigDecimal valorBase;

    public Long tipoImpostoId() {
        return tipoImpostoId;
    }

    public void tipoImpostoId(Long taxTypeId) {
        this.tipoImpostoId = taxTypeId;
    }

    public BigDecimal getvalorBase() {
        return BigDecimal.valueOf(valorBase);
    }

    public void setvalorBase(BigDecimal baseValue) {
        this.valorBase = baseValue;
    }
}
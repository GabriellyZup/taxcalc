package com.taxcalc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(name = "CalculationResponse", description = "DTO response para o cálculo do imposto")
public class CalculationResponseDTO {

    @JsonProperty("tipoImposto")
    @Schema(description = "Nome do imposto", example = "ICMS")
    private String tipoImpostoId;

    @JsonProperty("valorBase")
    @Schema(description = "Valor base o calculo", example = "1000.0")
    private BigDecimal valorBase;

    @JsonProperty("aliquota")
    @Schema(description = "Taxa de imposto devido", example = "18.0")
    private BigDecimal aliquota;

    @JsonProperty("valorImposto")
    @Schema(description = "Calculo do valor do imposto", example = "180.0")
    private BigDecimal valorImposto;

    @JsonProperty("valorTotal")
    @Schema(description = "Total  (base + imposto)", example = "1180.0")
    private BigDecimal valorTotal;

    public CalculationResponseDTO(
            String tipoImpostoId,
            BigDecimal valorBase,
            BigDecimal aliquota,
            BigDecimal valorImposto,
            BigDecimal valorTotal
    ) {
        this.tipoImpostoId = tipoImpostoId;
        this.valorBase = valorBase;
        this.aliquota = aliquota;
        this.valorImposto = valorImposto;
        this.valorTotal = valorTotal;
    }

    public String getTipoImpostoId() {
        return tipoImpostoId;
    }

    public BigDecimal getValorBase() {
        return valorBase;
    }

    public BigDecimal getAliquota() {
        return aliquota;
    }

    public BigDecimal getValorImposto() {
        return valorImposto;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }
}
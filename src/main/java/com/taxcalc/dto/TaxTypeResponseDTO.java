package com.taxcalc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taxcalc.model.TaxType;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public class TaxTypeResponseDTO {
    @JsonProperty("id")
    @Schema(description = "Unique identifier of the tax type", example = "1")
    private Long id;

    @JsonProperty("nome")
    @Schema(description = "Name of the tax", example = "ICMS")
    private String name;

    @JsonProperty("descricao")
    @Schema(description = "Detailed description", example = "Imposto sobre Circulação de Mercadorias e Serviços")
    private String description;

    @JsonProperty("aliquota")
    @Schema(description = "Tax rate percentage", example = "18.0")
    private BigDecimal taxRate;

    public TaxTypeResponseDTO(TaxType taxType) {
        this.id = taxType.getId();
        this.name = taxType.getName();
        this.description = taxType.getDescription();
        this.taxRate = taxType.getTaxRate();
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }
}
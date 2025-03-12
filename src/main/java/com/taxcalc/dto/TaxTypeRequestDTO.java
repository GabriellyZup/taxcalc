package com.taxcalc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(name = "TaxTypeRequest", description = "DTO para cadastro de novo tipo de imposto")


public class TaxTypeRequestDTO {
    @NotBlank(message = "Name is required")
    @JsonProperty("nome")
    @Schema(description = "Tax type name", example = "ICMS", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @JsonProperty("descricao")
    @Schema(description = "Tax description", example = "Imposto sobre Circulação de Mercadorias e Serviços")
    private String description;

    @JsonProperty("aliquota")
    @NotNull(message = "Tax rate is required")
    @Schema(description = "Tax rate percentage", example = "18.0", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal taxRate;

    //ver se isso aqui vai funcionar na test
    public TaxTypeRequestDTO(String ivaTest, BigDecimal bigDecimal, String testTax) {
    }

    public TaxTypeRequestDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
}
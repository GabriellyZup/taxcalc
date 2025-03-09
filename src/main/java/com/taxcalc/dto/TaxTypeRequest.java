package com.taxcalc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public class TaxTypeRequest {
    @NotBlank(message = "O nome é obrigatório")
    @JsonProperty("nome") // Mapeia o campo "nome" do JSON para "name"
    private String name;

    @JsonProperty("descricao")
    private String description;

    @DecimalMin(value = "0.0", message = "A alíquota não pode ser negativa")
    @DecimalMax(value = "100.0", message = "A alíquota não pode ultrapassar 100%")
    @JsonProperty("aliquota") // Mapeia "aliquota" do JSON para "taxRate"
    private Double taxRate;




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

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate; }
}
package com.taxcalc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class TaxTypeRequest {
    @NotBlank(message = "O nome é obrigatório")
    @JsonProperty("nome") // Mapeia o campo "nome" do JSON para "name"
    private String name;

    @JsonProperty("descricao")
    private String description;

    @JsonProperty("aliquota") // Mapeia "aliquota" do JSON para "taxRate"
    private BigDecimal taxRate;

    //ver se isso aqui vai funcionar na test
    public TaxTypeRequest(String ivaTest, BigDecimal bigDecimal, String testTax) {
    }


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
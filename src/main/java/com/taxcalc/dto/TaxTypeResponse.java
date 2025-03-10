package com.taxcalc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taxcalc.model.TaxType;

import java.math.BigDecimal;

public class TaxTypeResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("nome")
    private String name;

    @JsonProperty("descricao")
    private String description;

    @JsonProperty("aliquota")
    private BigDecimal taxRate;

    public TaxTypeResponse(TaxType taxType) {
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
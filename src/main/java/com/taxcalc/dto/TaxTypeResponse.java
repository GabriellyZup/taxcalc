package com.taxcalc.dto;

public class TaxTypeResponse {
    private Long id;
    private String name;
    private String description;
    private Double taxRate;

    public TaxTypeResponse(TaxType taxType) {
        this.id = taxType.getId();
        this.name = taxType.getName();
        this.description = taxType.getDescription();
        this.taxRate = taxType.getTaxRate();
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
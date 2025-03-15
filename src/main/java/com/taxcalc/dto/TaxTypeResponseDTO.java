package com.taxcalc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taxcalc.model.TaxType;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public class TaxTypeResponseDTO {
    @JsonProperty("id")
    @Schema(description = "Id do tipo de imposto", example = "1")
    private Long id;

    @JsonProperty("nome")
    @Schema(description = "Nome do imposto", example = "ICMS")
    private String nome;

    @JsonProperty("descricao")
    @Schema(description = "Descrição do imposto", example = "Imposto sobre Circulação de Mercadorias e Serviços")
    private String descricao;

    @JsonProperty("aliquota")
    @Schema(description = "Percentagem da taxa de imposto", example = "18.0")
    private BigDecimal aliquota;

    public TaxTypeResponseDTO(TaxType taxType) {
        this.id = taxType.getId();
        this.nome = taxType.getNome();
        this.descricao = taxType.getDescricao();
        this.aliquota = taxType.getAliquota();
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return nome;
    }

    public String getDescription() {
        return descricao;
    }

    public BigDecimal getAliquota() {
        return aliquota;
    }
}
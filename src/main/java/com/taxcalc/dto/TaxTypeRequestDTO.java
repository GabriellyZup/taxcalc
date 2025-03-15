package com.taxcalc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(name = "TaxTypeRequest", description = "DTO request para tipo de imposto")


public class TaxTypeRequestDTO {
    @NotBlank(message = "Nome é obrigatório")
    @JsonProperty("nome")
    @Schema(description = "Nome do tipo de imposto", example = "ICMS", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nome;

    @JsonProperty("descricao")
    @Schema(description = "Descrição do imposto", example = "Imposto sobre Circulação de Mercadorias e Serviços")
    private String descricao;

    @JsonProperty("aliquota")
    @NotNull(message = "A taxa de imposto é obrigatória")
    @Schema(description = "Percentagem da taxa de imposto", example = "18.0", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal aliquota;

    //ver se isso aqui vai funcionar na test
    public TaxTypeRequestDTO(String ivaTest, BigDecimal bigDecimal, String testTax) {
    }

    public TaxTypeRequestDTO() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
            this.nome = this.nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getAliquota() {
        return aliquota;
    }

    public void aliquota(BigDecimal aliquota) {
        this.aliquota = aliquota;
    }



}
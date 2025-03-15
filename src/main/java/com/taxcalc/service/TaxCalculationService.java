package com.taxcalc.service;


import com.taxcalc.dto.CalculationResponseDTO;
import com.taxcalc.model.TaxType;
import com.taxcalc.repository.TaxTypeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class TaxCalculationService {
    private final TaxTypeRepository taxTypeRepository;

    public TaxCalculationService(TaxTypeRepository taxTypeRepository) {
        this.taxTypeRepository = taxTypeRepository;
    }

    public BigDecimal calculateTax(Long taxTypeId, BigDecimal baseValue) {
        TaxType taxType = taxTypeRepository.findById(taxTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Imposto não localizado"));

        BigDecimal taxRateDecimal = taxType.getAliquota()
                .divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);

        return baseValue.multiply(taxRateDecimal)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public CalculationResponseDTO buildCalculationResponse(
            Long tipoImpostoId,
            BigDecimal valorBase,
            BigDecimal aliquota
    ) {
        TaxType taxType = taxTypeRepository.findById(tipoImpostoId)
                .orElseThrow(() -> new IllegalArgumentException("Imposto não localizado"));

        BigDecimal valorTotal = valorBase.add(aliquota);

        return new CalculationResponseDTO(
                taxType.getNome(),
                valorBase,
                taxType.getAliquota(),
                aliquota,
                valorTotal
        );
    }
}


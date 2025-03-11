package com.taxcalc.service;

import com.taxcalc.dto.CalculationResponseDTO;
import com.taxcalc.model.TaxType;
import com.taxcalc.repository.TaxTypeRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;

import java.math.BigDecimal;

@Service
public class TaxCalculationService {
    private final TaxTypeRepository taxTypeRepository;

    public TaxCalculationService(TaxTypeRepository taxTypeRepository) {
        this.taxTypeRepository = taxTypeRepository;
    }

    public BigDecimal calculateTax(Long taxTypeId, BigDecimal baseValue) {
        TaxType taxType = taxTypeRepository.findById(taxTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Tax type not found"));

        BigDecimal taxRateDecimal = taxType.getTaxRate()
                .divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);

        return baseValue.multiply(taxRateDecimal)
                .setScale(2, RoundingMode.HALF_UP);
    }

//ver esse retorno null

    public CalculationResponseDTO buildCalculationResponse(Long taxTypeId, Double baseValue, BigDecimal taxAmount) {
        return null;

    public CalculationResponseDTO buildCalculationResponse(
            Long taxTypeId,
            BigDecimal baseValue,
            BigDecimal taxAmount)
    {
        TaxType taxType = taxTypeRepository.findById(taxTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Tax type not found"));

        BigDecimal totalAmount = baseValue.add(taxAmount);

        return new CalculationResponseDTO(
                taxType.getName(),
                baseValue,
                taxType.getTaxRate(),
                taxAmount,
                totalAmount
        );

    }
}
package com.taxcalc.service;

import com.taxcalc.dto.CalculationResponseDTO;
import com.taxcalc.model.TaxType;
import com.taxcalc.repository.TaxTypeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TaxCalculationService {
    private final TaxTypeRepository taxTypeRepository;

    public TaxCalculationService(TaxTypeRepository taxTypeRepository) {
        this.taxTypeRepository = taxTypeRepository;
    }

    public double calculateTax(Long taxTypeId, double baseValue) {
        TaxType taxType = taxTypeRepository.findById(taxTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Tax type not found"));
        return baseValue * (taxType.getTaxRate() / 100);
    }

//ver esse retorno null

    public CalculationResponseDTO buildCalculationResponse(Long taxTypeId, Double baseValue, BigDecimal taxAmount) {
        return null;
    }
}
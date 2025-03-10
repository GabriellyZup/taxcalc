package com.taxcalc.repository;

import com.taxcalc.model.TaxType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TaxTypeRepositoryIntegrationTest {

    @Autowired
    private TaxTypeRepository taxTypeRepository;

    @Test
    void testCreateTaxType() {
        TaxType taxType = new TaxType();
        taxType.setName("ICMS");
        taxType.setTaxRate(BigDecimal.valueOf(18.0));
        taxType.setDescription("Imposto sobre mercadorias");

        TaxType savedTaxType = taxTypeRepository.save(taxType);
        assertNotNull(savedTaxType.getId());
    }
}
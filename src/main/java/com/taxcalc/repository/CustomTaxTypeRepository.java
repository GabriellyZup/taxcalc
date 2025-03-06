package com.taxcalc.repository;

import com.taxcalc.model.TaxType;
import java.util.Optional;

public interface CustomTaxTypeRepository {
    Optional<TaxType> findByName(String name);
}


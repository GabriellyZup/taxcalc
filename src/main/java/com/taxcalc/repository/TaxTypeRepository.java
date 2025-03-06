package com.taxcalc.repository;

import com.taxcalc.model.TaxType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxTypeRepository extends JpaRepository<TaxType, Long> {
}
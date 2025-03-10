package com.taxcalc.repository;

import com.taxcalc.model.TaxType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TaxTypeRepository extends JpaRepository<TaxType, Long> {
    Optional<TaxType> findByName(String name); // Spring Data implementa automaticamente!
}
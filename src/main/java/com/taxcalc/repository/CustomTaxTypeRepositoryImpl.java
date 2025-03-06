package com.taxcalc.repository;

import jakarta.persistence.EntityManager;

public class CustomTaxTypeRepositoryImpl implements CustomTaxTypeRepository {
    private final EntityManager entityManager;

    public CustomTaxTypeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<TaxType> findByName(String name) {
        // Implemente a busca por nome usando JPQL
    }
}
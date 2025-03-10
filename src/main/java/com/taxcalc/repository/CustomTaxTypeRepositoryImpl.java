package com.taxcalc.repository;

import com.taxcalc.model.TaxType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.Optional;

public class CustomTaxTypeRepositoryImpl implements CustomTaxTypeRepository {
    private final EntityManager entityManager;

    public CustomTaxTypeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<TaxType> findByName(String name) {
        TypedQuery<TaxType> query = entityManager.createQuery(
                "SELECT t FROM TaxType t WHERE t.name = :name", TaxType.class
        );
        query.setParameter("name", name);
        return query.getResultStream().findFirst();
    }
}

//"feat: implement CustomTaxTypeRepositoryImpl.findByName"
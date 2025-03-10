package com.taxcalc.repository;

import com.taxcalc.model.TaxType;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class TaxTypeRepositoryImpl implements CustomTaxTypeRepository {
    private final EntityManager entityManager;

    public TaxTypeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<TaxType> findByName(String name) {
        // Mantenha apenas se a query for complexa (ex: joins, filtros especiais)
        return entityManager.createQuery(
                        "SELECT t FROM TaxType t WHERE t.name = :name", TaxType.class)
                .setParameter("name", name)
                .getResultStream()
                .findFirst();
    }
}
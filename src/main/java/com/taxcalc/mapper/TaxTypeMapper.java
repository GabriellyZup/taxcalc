// TaxTypeMapper.java
package com.taxcalc.mapper;

import com.taxcalc.dto.request.TaxTypeRequest;
import com.taxcalc.dto.response.TaxTypeResponse;
import com.taxcalc.model.TaxType;

public class TaxTypeMapper {
    public static TaxType toEntity(TaxTypeRequest request) {
        TaxType taxType = new TaxType();
        taxType.setName(request.name());
        taxType.setTaxRate(request.rate().doubleValue());
        taxType.setDescription(request.description());
        return taxType;
    }

    public static TaxTypeResponse toResponse(TaxType entity) {
        return new TaxTypeResponse(
                entity.getId(),
                entity.getName(),
                entity.getTaxRate(),
                entity.getDescription(),
                null // Temporário - precisamos adicionar createdDate na entidade
        );
    }
}
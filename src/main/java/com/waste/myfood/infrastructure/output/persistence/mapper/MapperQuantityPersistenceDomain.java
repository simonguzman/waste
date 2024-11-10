package com.waste.myfood.infrastructure.output.persistence.mapper;

import com.waste.myfood.domain.value_objects.QuantityWaste;
import com.waste.myfood.infrastructure.output.persistence.entities.QuantityWasteEntity;

public class MapperQuantityPersistenceDomain {
    public static QuantityWaste mapPersistenceDomain(QuantityWasteEntity persistence) {
        return new QuantityWaste(persistence.getIdQuantityWaste(), persistence.getWasteQuantity(),
                persistence.getTotalWasteQuantity());
    }

    public static QuantityWasteEntity mapPersistenceDomain(QuantityWaste domain) {
        return new QuantityWasteEntity(domain.getId(), domain.getWasteQuantity(), domain.getTotalWasteQuantity());
    }

}

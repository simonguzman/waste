package com.waste.myfood.infrastructure.output.persistence.mapper;

import com.waste.myfood.domain.value_objects.ProductWaste;
import com.waste.myfood.infrastructure.output.persistence.entities.ProductWasteEntity;

public class MapperProductPersistenceDomain {
    public static ProductWaste mapPersistenceDomain(ProductWasteEntity entity) {
        return new ProductWaste(entity.getId(), entity.getName(), entity.getCategory(), entity.getStock());
    }

    public static ProductWasteEntity mapDomainPersistence(ProductWaste domain) {
        return new ProductWasteEntity(domain.getId(), domain.getName(), domain.getCategory(), domain.getStock());
    }
}

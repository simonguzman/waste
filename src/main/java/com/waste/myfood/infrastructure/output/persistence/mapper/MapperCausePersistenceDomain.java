package com.waste.myfood.infrastructure.output.persistence.mapper;

import java.util.ArrayList;
import java.util.List;

import com.waste.myfood.domain.value_objects.CauseWaste;
import com.waste.myfood.infrastructure.output.persistence.entities.CauseWasteEntity;

public class MapperCausePersistenceDomain {
    public static CauseWaste mapPersistenceDomain(CauseWasteEntity persistence) {
        return new CauseWaste(persistence.getIdCauseWaste(), persistence.getDescription());
    }

    public static List<CauseWaste> mapPersistenceDomain(List<CauseWasteEntity> persistence) {
        List<CauseWaste> response = new ArrayList<>();
        for (CauseWasteEntity entity : persistence)
            response.add(mapPersistenceDomain(entity));
        return response;
    }

    public static CauseWasteEntity mapDomainPersistence(CauseWaste domain) {
        return new CauseWasteEntity(domain.getId(), domain.getDescription());
    }

    public static List<CauseWasteEntity> mapDomainPersistence(List<CauseWaste> domain) {
        List<CauseWasteEntity> response = new ArrayList<>();
        for (CauseWaste obj : domain)
            response.add(mapDomainPersistence(obj));
        return response;
    }
}

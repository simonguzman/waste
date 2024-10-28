package com.waste.myfood.infrastructure.output.persistence.mapper;

import org.hibernate.mapping.List;

import com.waste.myfood.application.output.ExceptionFormatterIntPort;
import com.waste.myfood.domain.agregates.Waste;
import com.waste.myfood.domain.value_objects.CauseWaste;
import com.waste.myfood.domain.value_objects.ProductWaste;
import com.waste.myfood.domain.value_objects.QuantityWaste;
import com.waste.myfood.infrastructure.output.persistence.entities.WasteEntity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MapperWastePersistenceDomain {
    public final ExceptionFormatterIntPort formatter;

    public Waste persistenceToDomain(WasteEntity entity) {
        ProductWaste product = new ProductWaste(entity.getProduct().getId(), entity.getProduct().getName(), entity.getProduct().getStock());
        QuantityWaste quantityWaste = new QuantityWaste(entity.getWasteQuantity());
        CauseWaste cause = new CauseWaste(entity.getCauseDescription());
        return new Waste(entity.getWasteId(), product, quantityWaste, cause, entity.getDateRegister());
    }

    public WasteEntity domainToPersistence(Waste domain) {
        return new WasteEntity(
            domain.getIdWaste(),
            domain.getProduct().getId(),
            domain.getProduct().getName(),
            domain.getProduct().getStock(),
            domain.getQuantityWaste().getTotalWasteQuantity(),
            domain.getCause().getDescription(),
            domain.getDateRegister()
        );
    }

    public List<Waste> persistenceToDomain(List<WasteEntity> entities) {
        List<Waste> domainList = new ArrayList<>();
        for (WasteEntity entity : entities) {
            domainList.add(this.persistenceToDomain(entity));
        }
        return domainList;
    }

    public List<WasteEntity> domainToPersistence(List<Waste> wastes) {
        List<WasteEntity> entityList = new ArrayList<>();
        for (Waste waste : wastes) {
            entityList.add(this.domainToPersistence(waste));
        }
        return entityList;
    
}

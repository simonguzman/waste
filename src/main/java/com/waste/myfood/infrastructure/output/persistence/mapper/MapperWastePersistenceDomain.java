package com.waste.myfood.infrastructure.output.persistence.mapper;

import java.util.ArrayList;
import java.util.List;

import com.waste.myfood.application.output.ExceptionFormatterIntPort;
import com.waste.myfood.domain.agregates.Waste;
import com.waste.myfood.domain.constants.CauseWasteConstants;
import com.waste.myfood.domain.value_objects.CauseWaste;
import com.waste.myfood.domain.value_objects.ProductWaste;
import com.waste.myfood.domain.value_objects.QuantityWaste;
import com.waste.myfood.infrastructure.output.persistence.entities.CauseWasteEntity;
import com.waste.myfood.infrastructure.output.persistence.entities.ProductWasteEntity;
import com.waste.myfood.infrastructure.output.persistence.entities.QuantityWasteEntity;
import com.waste.myfood.infrastructure.output.persistence.entities.WasteEntity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MapperWastePersistenceDomain {
    public final ExceptionFormatterIntPort formatter;

    public Waste persistenceToDomain(WasteEntity entity) {
        ProductWaste product = new ProductWaste(entity.getProduct().getId(), entity.getProduct().getName(), entity.getProduct().getStock());
        QuantityWaste quantityWaste = new QuantityWaste(entity.getQuantityWaste().getWasteQuantity());
        CauseWaste cause = new CauseWaste();
        return new Waste(entity.getIdWaste(), product, quantityWaste, cause, entity.getDateRegister());
    }

    public WasteEntity domainToPersistence(Waste domain) {
        ProductWasteEntity productEntity = new ProductWasteEntity(
            domain.getProduct().getId(),
            domain.getProduct().getName(),
            domain.getProduct().getCategory(),
            domain.getProduct().getStock()
        );

        QuantityWasteEntity quantityEntity = new QuantityWasteEntity(
            null, // ID se generará automáticamente
            domain.getQuantityWaste().getTotalWasteQuantity(),
            domain.getQuantityWaste().getTotalWasteQuantity()
        );

        CauseWasteEntity causeEntity = new CauseWasteEntity(
            null, // ID se generará automáticamente
            domain.getCause().getDescription()
        );

        return new WasteEntity(
            domain.getIdWaste(),
            productEntity,
            quantityEntity,
            causeEntity,
            domain.getDateRegister()
        );
    }

    public List<Waste> persistenceToDomain(List<WasteEntity> entities) {
        List<Waste> domainList = new ArrayList<>();
        entities.forEach(entity -> domainList.add(persistenceToDomain(entity)));
        return domainList;
    }

    public List<WasteEntity> domainToPersistence(List<Waste> wastes) {
        List<WasteEntity> entityList = new ArrayList<>();
        wastes.forEach(waste -> entityList.add(domainToPersistence(waste)));
        return entityList;
    }

    private int determineCauseIndex(String causeDescription) {
        // Implementa la lógica para mapear la descripción a un índice
        return switch (causeDescription) {
            case CauseWasteConstants.CAUSE_WASTE_EXPIRED -> 1;
            case CauseWasteConstants.CAUSE_WASTE_EXCESS_PREPARATION -> 2;
            case CauseWasteConstants.CAUSE_WASTE_BAD_STORAGE -> 3;
            case CauseWasteConstants.CAUSE_WASTE_PREPARATION_ERROR -> 4;
            case CauseWasteConstants.CAUSE_WASTE_CUSTOMER_RETURN -> 5;
            case CauseWasteConstants.CAUSE_WASTE_TRANSPORTER_DAMAGE -> 6;
            case CauseWasteConstants.CAUSE_WASTE_WEATHER_CONDITIONS -> 7;
            case CauseWasteConstants.CAUSE_WASTE_HANDLING_DAMAGE -> 8;
            case CauseWasteConstants.CAUSE_WASTE_DEFECTIVE_INGREDIENTS -> 9;
            case CauseWasteConstants.CAUSE_WASTE_EXCESS_PORTIONS -> 10;
            case CauseWasteConstants.CAUSE_WASTE_CANCELED_ORDER -> 11;
            case CauseWasteConstants.CAUSE_WASTE_CROSS_CONTAMINATION -> 12;
            default -> 0; // Índice predeterminado en caso de no encontrar una causa coincidente
        };
    }
}


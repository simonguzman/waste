package com.waste.myfood.infrastructure.output.persistence.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        ProductWaste product = new ProductWaste(
        entity.getProduct().getName(),
        entity.getProduct().getCategory(),
        entity.getProduct().getStock()
    );
    QuantityWaste quantityWaste = new QuantityWaste(
        entity.getQuantityWaste().getWasteQuantity()
    );
    CauseWaste cause = new CauseWaste(
        entity.getCauseWaste().getIdCauseWaste(),
        entity.getCauseWaste().getDescription()
    );
    return new Waste(entity.getIdWaste(), product, quantityWaste, cause, entity.getDateRegister());
    }

    public WasteEntity domainToPersistence(Waste domain) {
        System.out.println("Convirtiendo Waste a WasteEntity: " + domain);
        
        // Log detallado de Product
        System.out.println("Product details:");
        System.out.println("- ID: " + domain.getProduct().getId());
        System.out.println("- Name: " + domain.getProduct().getName());
        System.out.println("- Category: " + domain.getProduct().getCategory());
        System.out.println("- Stock: " + domain.getProduct().getStock());

        ProductWasteEntity productEntity = new ProductWasteEntity(
            domain.getProduct().getId() != null ? domain.getProduct().getId() : UUID.randomUUID().toString(),
            domain.getProduct().getName(),
            domain.getProduct().getCategory(),
            domain.getProduct().getStock()
        );

        System.out.println("QuantityWaste details:");
        System.out.println("- ID: " + domain.getQuantityWaste().getId());
        System.out.println("- Quantity: " + domain.getQuantityWaste().getWasteQuantity());
        System.out.println("- Total: " + domain.getQuantityWaste().getTotalWasteQuantity());

        QuantityWasteEntity quantityEntity = new QuantityWasteEntity(
            domain.getQuantityWaste().getId() != null ? domain.getQuantityWaste().getId() : UUID.randomUUID().toString(),
            domain.getQuantityWaste().getWasteQuantity(),
            domain.getQuantityWaste().getTotalWasteQuantity()
        );

        System.out.println("CauseWaste details:");
        System.out.println("- ID: " + domain.getCause().getId());
        System.out.println("- Description: " + domain.getCause().getDescription());

        CauseWasteEntity causeEntity = new CauseWasteEntity(
            domain.getCause().getId() != null ? domain.getCause().getId() : UUID.randomUUID().toString(),
            domain.getCause().getDescription() != null ? domain.getCause().getDescription() : "Default Cause"
        );

        System.out.println("Creando WasteEntity con causeWaste: " + causeEntity);

        System.out.println("Entities created:");
        System.out.println("- ProductEntity: " + productEntity);
        System.out.println("- QuantityEntity: " + quantityEntity);
        System.out.println("- CauseEntity: " + causeEntity);

        WasteEntity wasteEntity = new WasteEntity(
            domain.getIdWaste(),
            productEntity,
            quantityEntity,
            causeEntity,
            domain.getDateRegister()
        );

        System.out.println("WasteEntity creada: " + wasteEntity);
        System.out.println("WasteEntity final state:");
        System.out.println("- ID: " + wasteEntity.getIdWaste());
        System.out.println("- Product: " + wasteEntity.getProduct());
        System.out.println("- Quantity: " + wasteEntity.getQuantityWaste());
        System.out.println("- Cause: " + wasteEntity.getCauseWaste());
        System.out.println("- Date: " + wasteEntity.getDateRegister());
        
        return wasteEntity;
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


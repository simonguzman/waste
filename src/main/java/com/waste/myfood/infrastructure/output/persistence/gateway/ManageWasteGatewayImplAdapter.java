package com.waste.myfood.infrastructure.output.persistence.gateway;

import java.util.List;

import org.springframework.stereotype.Service;

import com.waste.myfood.application.output.ManageWasteGatewayIntPort;
import com.waste.myfood.domain.agregates.Waste;
import com.waste.myfood.infrastructure.output.persistence.entities.CauseWasteEntity;
import com.waste.myfood.infrastructure.output.persistence.entities.ProductWasteEntity;
import com.waste.myfood.infrastructure.output.persistence.entities.QuantityWasteEntity;
import com.waste.myfood.infrastructure.output.persistence.entities.WasteEntity;
import com.waste.myfood.infrastructure.output.persistence.mapper.MapperWastePersistenceDomain;
import com.waste.myfood.infrastructure.output.persistence.repositories.WasteRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class ManageWasteGatewayImplAdapter implements ManageWasteGatewayIntPort{

    private final WasteRepository serviceDB;
    private final MapperWastePersistenceDomain mapper;
    private final EntityManager entityManager;

    public ManageWasteGatewayImplAdapter(WasteRepository serviceDB, MapperWastePersistenceDomain mapper, EntityManager entityManager){
        this.serviceDB = serviceDB;
        this.mapper = mapper;
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Waste saveWaste(Waste waste) {
        try {
            // Primero verificar si ya existe alguna de las entidades
            ProductWasteEntity existingProduct = entityManager.find(ProductWasteEntity.class, waste.getProduct().getId());
            QuantityWasteEntity existingQuantity = entityManager.find(QuantityWasteEntity.class, waste.getQuantityWaste().getId());
            CauseWasteEntity existingCause = entityManager.find(CauseWasteEntity.class, waste.getCause().getId());

            // Crear o recuperar las entidades relacionadas
            ProductWasteEntity productEntity;
            if (existingProduct == null) {
                productEntity = new ProductWasteEntity(
                    waste.getProduct().getId(),
                    waste.getProduct().getName(),
                    waste.getProduct().getCategory(),
                    waste.getProduct().getStock()
                );
                entityManager.persist(productEntity);
            } else {
                productEntity = existingProduct;
            }

            QuantityWasteEntity quantityEntity;
            if (existingQuantity == null) {
                quantityEntity = new QuantityWasteEntity(
                    waste.getQuantityWaste().getId(),
                    waste.getQuantityWaste().getWasteQuantity(),
                    waste.getQuantityWaste().getTotalWasteQuantity()
                );
                entityManager.persist(quantityEntity);
            } else {
                quantityEntity = existingQuantity;
            }

            CauseWasteEntity causeEntity;
            if (existingCause == null) {
                causeEntity = new CauseWasteEntity(
                    waste.getCause().getId(),
                    waste.getCause().getDescription()
                );
                entityManager.persist(causeEntity);
            } else {
                causeEntity = existingCause;
            }

            entityManager.flush();

            // Crear y guardar la entidad Waste
            WasteEntity wasteEntity = new WasteEntity(
                waste.getIdWaste(),
                productEntity,
                quantityEntity,
                causeEntity,
                waste.getDateRegister()
            );

            WasteEntity savedEntity = serviceDB.save(wasteEntity);
            return mapper.persistenceToDomain(savedEntity);

        } catch (Exception e) {
            System.err.println("Error saving waste: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Waste> findAll() {
        List<WasteEntity> wasteEntities = serviceDB.findAll();
        return mapper.persistenceToDomain(wasteEntities);
    }

    @Override
    public Waste findById(String wasteId) {
        return serviceDB.findById(wasteId)
                .map(mapper::persistenceToDomain)
                .orElse(null);
    }

    @Override
    public List<Waste> findByProductId(String productId) {
        List<WasteEntity> wasteEntities = serviceDB.findAllByProduct_Id(productId);
        return mapper.persistenceToDomain(wasteEntities);
    }

    @Override
    public List<Waste> findByCause(String cause) {
        List<WasteEntity> wasteEntities = serviceDB.findAllByCauseWaste_Description(cause);
        return mapper.persistenceToDomain(wasteEntities);
    }

    
}

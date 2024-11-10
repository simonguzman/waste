package com.waste.myfood.infrastructure.output.persistence.gateway;

import java.util.ArrayList;
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
public class ManageWasteGatewayImplAdapter implements ManageWasteGatewayIntPort {

    private final WasteRepository serviceDB;
    private final MapperWastePersistenceDomain mapper;
    private final EntityManager entityManager;

    public ManageWasteGatewayImplAdapter(WasteRepository serviceDB, MapperWastePersistenceDomain mapper,
            EntityManager entityManager) {
        this.serviceDB = serviceDB;
        this.mapper = mapper;
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Waste saveWaste(Waste waste) {
        try {
            // Verifica si es una actualización buscando el waste existente
            WasteEntity existingWaste = entityManager.find(WasteEntity.class, waste.getIdWaste());
            boolean isUpdate = (existingWaste != null);

            // Si es una actualización, usa las entidades existentes
            if (isUpdate) {
                // Actualiza el producto existente
                ProductWasteEntity productEntity = existingWaste.getProduct();
                productEntity.setName(waste.getProduct().getName());
                productEntity.setCategory(waste.getProduct().getCategory());
                productEntity.setStock(waste.getProduct().getStock());

                // Actualiza la cantidad existente
                QuantityWasteEntity quantityEntity = existingWaste.getQuantityWaste();
                quantityEntity.setWasteQuantity(waste.getQuantityWaste().getWasteQuantity());
                quantityEntity.setTotalWasteQuantity(waste.getQuantityWaste().getTotalWasteQuantity());

                // Actualiza la causa existente
                CauseWasteEntity causeEntity = existingWaste.getCauseWaste();
                causeEntity.setDescription(waste.getCause().getDescription());

                // Actualiza el waste
                existingWaste.setDateRegister(waste.getDateRegister());

                return mapper.persistenceToDomain(serviceDB.save(existingWaste));
            } else {
                // Si es una creación nueva, usa el código existente
                ProductWasteEntity productEntity = new ProductWasteEntity();
                productEntity.setId(waste.getProduct().getId());
                productEntity.setName(waste.getProduct().getName());
                productEntity.setCategory(waste.getProduct().getCategory());
                productEntity.setStock(waste.getProduct().getStock());
                entityManager.persist(productEntity);

                QuantityWasteEntity quantityEntity = new QuantityWasteEntity();
                quantityEntity.setIdQuantityWaste(waste.getQuantityWaste().getId());
                quantityEntity.setWasteQuantity(waste.getQuantityWaste().getWasteQuantity());
                quantityEntity.setTotalWasteQuantity(waste.getQuantityWaste().getTotalWasteQuantity());
                entityManager.persist(quantityEntity);

                CauseWasteEntity causeEntity = new CauseWasteEntity();
                causeEntity.setIdCauseWaste(waste.getCause().getId());
                causeEntity.setDescription(waste.getCause().getDescription());
                entityManager.persist(causeEntity);

                WasteEntity wasteEntity = new WasteEntity();
                wasteEntity.setIdWaste(waste.getIdWaste());
                wasteEntity.setProduct(productEntity);
                wasteEntity.setQuantityWaste(quantityEntity);
                wasteEntity.setCauseWaste(causeEntity);
                wasteEntity.setDateRegister(waste.getDateRegister());

                return mapper.persistenceToDomain(serviceDB.save(wasteEntity));
            }
        } catch (Exception e) {
            System.err.println("Error saving/updating waste: " + e.getMessage());
            e.printStackTrace();
            return null;
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
        try {
            List<WasteEntity> wasteEntities = serviceDB.findAllByProduct_Id(productId);
            if (wasteEntities.isEmpty()) {
                System.out.println("No se encontraron registros de desperdicio para el producto ID: " + productId);
                return new ArrayList<>();
            }

            // Ordenar por fecha de registro, del más reciente al más antiguo
            wasteEntities.sort((w1, w2) -> w2.getDateRegister().compareTo(w1.getDateRegister()));

            return mapper.persistenceToDomain(wasteEntities);
        } catch (Exception e) {
            System.err.println("Error buscando desperdicios por ID de producto: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Waste> findByCause(String cause) {
        List<WasteEntity> wasteEntities = serviceDB.findAllByCauseWaste_Description(cause);
        return mapper.persistenceToDomain(wasteEntities);
    }

    @Override
    public List<Waste> saveAll(List<Waste> wastes) {
        return this.mapper.persistenceToDomain(this.serviceDB.saveAll(this.mapper.domainToPersistence(wastes)));
    }

}

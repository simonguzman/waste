package com.waste.myfood.infrastructure.output.persistence.gateway;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.waste.myfood.application.output.ManageProductGatewayIntPort;
import com.waste.myfood.domain.value_objects.ProductWaste;
import com.waste.myfood.infrastructure.output.persistence.entities.ProductWasteEntity;
import com.waste.myfood.infrastructure.output.persistence.mapper.MapperProductPersistenceDomain;
import com.waste.myfood.infrastructure.output.persistence.repositories.ProductRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ManageProductGatewayIImplAdapter implements ManageProductGatewayIntPort {

    private final ProductRepository serviceDB;

    @Override
    public ProductWaste findById(String id) {
        Optional<ProductWasteEntity> response = this.serviceDB.findById(id);
        if (!response.isPresent())
            return null;
        return MapperProductPersistenceDomain.mapPersistenceDomain(response.get());
    }

    @Override
    public boolean existById(String id) {
        return this.serviceDB.existsById(id);
    }

    @Override
    public ProductWaste save(ProductWaste product) {
        return MapperProductPersistenceDomain.mapPersistenceDomain(
                this.serviceDB.save(MapperProductPersistenceDomain.mapDomainPersistence(product)));
    }

}

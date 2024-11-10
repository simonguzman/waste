package com.waste.myfood.infrastructure.output.persistence.gateway;

import org.springframework.stereotype.Service;

import com.waste.myfood.application.output.ManageQuantityGatewayIntPort;
import com.waste.myfood.domain.value_objects.QuantityWaste;
import com.waste.myfood.infrastructure.output.persistence.mapper.MapperCausePersistenceDomain;
import com.waste.myfood.infrastructure.output.persistence.mapper.MapperQuantityPersistenceDomain;
import com.waste.myfood.infrastructure.output.persistence.repositories.QuantityRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ManageQuantityGatewayImplAdapter implements ManageQuantityGatewayIntPort {

    private final QuantityRepository serviceDB;

    @Override
    public QuantityWaste save(QuantityWaste quantity) {
        return MapperQuantityPersistenceDomain.mapPersistenceDomain(
                this.serviceDB.save(MapperQuantityPersistenceDomain.mapPersistenceDomain(quantity)));
    }

}

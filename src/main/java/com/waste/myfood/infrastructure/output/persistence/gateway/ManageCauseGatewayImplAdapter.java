package com.waste.myfood.infrastructure.output.persistence.gateway;

import java.util.List;

import org.springframework.stereotype.Service;

import com.waste.myfood.application.output.ManageCauseGatewayIntPort;
import com.waste.myfood.domain.value_objects.CauseWaste;
import com.waste.myfood.infrastructure.output.persistence.mapper.MapperCausePersistenceDomain;
import com.waste.myfood.infrastructure.output.persistence.repositories.CauseRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ManageCauseGatewayImplAdapter implements ManageCauseGatewayIntPort {
    private final CauseRepository serviceDB;

    @Override
    public List<CauseWaste> findByName(String name) {
        return MapperCausePersistenceDomain.mapPersistenceDomain(this.serviceDB.findByDescription(name));
    }

    @Override
    public CauseWaste save(CauseWaste cause) {
        return MapperCausePersistenceDomain
                .mapPersistenceDomain(this.serviceDB.save(MapperCausePersistenceDomain.mapDomainPersistence(cause)));
    }

}

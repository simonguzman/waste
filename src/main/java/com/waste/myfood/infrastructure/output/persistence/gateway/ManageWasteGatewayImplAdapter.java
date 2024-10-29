package com.waste.myfood.infrastructure.output.persistence.gateway;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.waste.myfood.application.output.ManageWasteGatewayIntPort;
import com.waste.myfood.domain.agregates.Waste;
import com.waste.myfood.infrastructure.output.persistence.entities.WasteEntity;
import com.waste.myfood.infrastructure.output.persistence.repositories.WasteRepository;

@Service
public class ManageWasteGatewayImplAdapter implements ManageWasteGatewayIntPort{

    private final WasteRepository serviceDB;
    private final ModelMapper mapper;

    public ManageWasteGatewayImplAdapter(WasteRepository serviceDB, ModelMapper mapper){
        this.serviceDB = serviceDB;
        this.mapper = mapper;
    }

    @Override
    public Waste saveWaste(Waste waste) {
        WasteEntity wasteEntity = mapper.map(waste, WasteEntity.class);
        WasteEntity savedEntity = serviceDB.save(wasteEntity);
        return mapper.map(savedEntity, Waste.class);
    }

    @Override
    public List<Waste> findAll() {
        List<WasteEntity> wasteEntities = serviceDB.findAll();
        return mapper.map(wasteEntities, new TypeToken<List<Waste>>() {}.getType());
    }

    @Override
    public Waste findById(String wasteId) {
        return serviceDB.findById(wasteId)
                .map(entity -> mapper.map(entity, Waste.class))
                .orElse(null);
    }

    @Override
    public List<Waste> findByProductId(String productId) {
        List<WasteEntity> wasteEntities = serviceDB.findAllByProduct_Id(productId);
        return mapper.map(wasteEntities, new TypeToken<List<Waste>>() {}.getType());
    }

    @Override
    public List<Waste> findByCause(String cause) {
        List<WasteEntity> wasteEntities = serviceDB.findAllByCauseWaste_Description(cause);
        return mapper.map(wasteEntities, new TypeToken<List<Waste>>() {}.getType());
    }
}

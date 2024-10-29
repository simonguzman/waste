package com.waste.myfood.infrastructure.input.mappers;

import java.util.ArrayList;
import java.util.List;

import com.waste.myfood.application.output.ExceptionFormatterIntPort;
import com.waste.myfood.domain.agregates.Waste;
import com.waste.myfood.domain.value_objects.CauseWaste;
import com.waste.myfood.domain.value_objects.ProductWaste;
import com.waste.myfood.domain.value_objects.QuantityWaste;
import com.waste.myfood.infrastructure.input.dto.request.WasteDTORequest;
import com.waste.myfood.infrastructure.input.dto.response.WasteDTOResponse;
import com.waste.myfood.infrastructure.output.persistence.entities.CauseWasteEntity;
import com.waste.myfood.infrastructure.output.persistence.entities.ProductWasteEntity;
import com.waste.myfood.infrastructure.output.persistence.entities.QuantityWasteEntity;
import com.waste.myfood.infrastructure.output.persistence.entities.WasteEntity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MapperWasteInfraestructureDomain {
    public final ExceptionFormatterIntPort formatter;

    public Waste infrastructureToDomain(WasteDTORequest request){
        ProductWaste product = request.getProduct();
        QuantityWaste quantityWaste = request.getQuantityWaste();
        CauseWaste cause = request.getCause();
        return new Waste(request.getIdWaste(), product, quantityWaste, cause, request.getDateRegister());
    }

    public WasteDTOResponse domainToInfrastructure(Waste domain){
        return new WasteDTOResponse(domain.getIdWaste(), domain.getProduct(), domain.getQuantityWaste(), domain.getCause(), domain.getDateRegister());
    }

    public WasteEntity domainToEntity(Waste domain) {
        // Mapping the domain Waste to the entity WasteEntity
        ProductWasteEntity productEntity = new ProductWasteEntity(domain.getProduct().getId(),domain.getProduct().getName(),domain.getProduct().getCategory(),domain.getProduct().getStock()); 
        QuantityWasteEntity quantityEntity = new QuantityWasteEntity(domain.getQuantityWaste().getId(),domain.getQuantityWaste().getWasteQuantity(), domain.getQuantityWaste().getTotalWasteQuantity()); 
        CauseWasteEntity causeEntity = new CauseWasteEntity(domain.getCause().getId(),domain.getCause().getDescription()); 

        return new WasteEntity(
                domain.getIdWaste(),
                productEntity,
                quantityEntity,
                causeEntity,
                domain.getDateRegister()
        );
    }

    public Waste entityToDomain(WasteEntity entity) {
        ProductWaste product = new ProductWaste(entity.getProduct().getName(),entity.getProduct().getCategory(), entity.getProduct().getStock());
        QuantityWaste quantityWaste = new QuantityWaste(entity.getQuantityWaste().getWasteQuantity());
        CauseWaste cause = new CauseWaste(entity.getCauseWaste().getIdCauseWaste(), entity.getCauseWaste().getDescription());
        return new Waste(
                entity.getIdWaste(),
                product,
                quantityWaste,
                cause,
                entity.getDateRegister()
        );
    }

    public List<Waste> infrastructureToDomain(List<WasteDTORequest> requests){
        List<Waste> domainList = new ArrayList<>();
        for(WasteDTORequest request : requests){
            domainList.add(infrastructureToDomain(request));
        }
        return domainList;
    }

    public List<WasteDTOResponse> domainToInfrastructure(List<Waste> wastes){
        List<WasteDTOResponse> responseList = new ArrayList<>();
        for(Waste waste : wastes){
            responseList.add(this.domainToInfrastructure(waste));
        }
        return responseList;
    }
}

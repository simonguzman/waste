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

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MapperProductInfraestructureDomain {
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

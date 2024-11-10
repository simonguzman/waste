package com.waste.myfood.infrastructure.input.mappers;

import java.util.ArrayList;
import java.util.List;

import com.waste.myfood.domain.value_objects.ProductWaste;
import com.waste.myfood.infrastructure.input.dto.request.ProductWasteDTORequest;

public class MapperProductInfraestructureDomain {
    public static ProductWaste mapInfraestructureDomain(ProductWasteDTORequest infraestructure) {
        return new ProductWaste(infraestructure.getId(), infraestructure.getName(), infraestructure.getCategory(),
                infraestructure.getStock());
    }

    public static List<ProductWaste> mapInfraestructureDomain(List<ProductWasteDTORequest> infraestructure) {
        List<ProductWaste> response = new ArrayList<>();
        for (ProductWasteDTORequest value : infraestructure)
            response.add(mapInfraestructureDomain(value));
        return response;
    }

    public static ProductWasteDTORequest mapDomainInfraestructure(ProductWaste domain) {
        return new ProductWasteDTORequest(domain.getId(), domain.getName(), domain.getCategory(),
                domain.getStock());
    }

    public static List<ProductWasteDTORequest> mapDomainInfraestructure(List<ProductWaste> infraestructure) {
        List<ProductWasteDTORequest> response = new ArrayList<>();
        for (ProductWaste value : infraestructure)
            response.add(mapDomainInfraestructure(value));
        return response;
    }

}

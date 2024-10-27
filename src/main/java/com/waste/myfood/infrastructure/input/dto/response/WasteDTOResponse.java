package com.waste.myfood.infrastructure.input.dto.response;

import java.util.Date;

import com.waste.myfood.domain.value_objects.CauseWaste;
import com.waste.myfood.domain.value_objects.ProductWaste;
import com.waste.myfood.domain.value_objects.QuantityWaste;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WasteDTOResponse {
    private String idWaste;
    private ProductWaste product;
    private QuantityWaste quantityWaste;
    private CauseWaste cause;
    private Date dateRegister; 
}

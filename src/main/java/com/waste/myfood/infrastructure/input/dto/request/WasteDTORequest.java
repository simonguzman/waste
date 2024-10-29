package com.waste.myfood.infrastructure.input.dto.request;

import java.util.Date;

import com.waste.myfood.domain.value_objects.CauseWaste;
import com.waste.myfood.domain.value_objects.ProductWaste;
import com.waste.myfood.domain.value_objects.QuantityWaste;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WasteDTORequest {
    @NotBlank(message = "You must provide the waste identifier.")
    private String idWaste;
    @NotNull(message = "You must provide the product associated with the waste.")
    private ProductWaste product;
    @NotNull(message = "You must provide the quantity of waste.")
    private QuantityWaste quantityWaste;
    @NotNull(message = "You must provide the cause of waste.")
    private CauseWaste cause;
    private Date dateRegister; 
}


package com.waste.myfood.infrastructure.input.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductWasteDTORequest {
    private String id;
    private String name;
    private String category;
    private Double stock;
}

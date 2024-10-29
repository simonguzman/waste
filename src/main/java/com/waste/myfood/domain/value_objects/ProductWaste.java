package com.waste.myfood.domain.value_objects;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductWaste {
    private String id;
    private String name;
    private String category;
    private double stock;

    public ProductWaste(String name, String category, double stock){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.category = category;
        this.stock = stock;
    }
        
        /**
     * Devuelve los detalles del producto asociado al desperdicio en formato de cadena.
     *
     * @return Una cadena que contiene el ID, nombre, categoría y stock del producto.
     */
    public String detailsProduct() {
        return String.format("ID: %s, Nombre: %s, Categoria: %s, Stock: %.2f",
                             id, name, category, stock);
                             
    }

}


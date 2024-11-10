package com.waste.myfood.domain.value_objects;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductWaste {
    private String id;
    private String name;
    private String category;
    private double stock;

    public ProductWaste(String name, String category, double stock) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.category = category;
        this.stock = stock;
    }

    public boolean isValid() {
        if (this.id.isBlank())
            return false;
        if (this.name.isBlank())
            return false;
        if (this.category.isBlank())
            return false;
        if (this.stock < 0)
            return false;
        return true;
    }

    /**
     * Devuelve los detalles del producto asociado al desperdicio en formato de
     * cadena.
     *
     * @return Una cadena que contiene el ID, nombre, categoría y stock del
     *         producto.
     */
    public String detailsProduct() {
        return String.format("ID: %s, Nombre: %s, Categoria: %s, Stock: %.2f",
                id, name, category, stock);

    }

}

package com.waste.myfood.domain.value_objects;

import java.util.UUID;

import lombok.Getter;

@Getter
public class ProductWaste {
    private String productWasteId;
        private Product product;

        public ProductWaste(Product product){
            this.productWasteId = UUID.randomUUID().toString();
            this.product = product;
        }
        
        /**
     * Devuelve los detalles del producto asociado al desperdicio en formato de cadena.
     *
     * @return Una cadena que contiene el ID, nombre, categoría y stock del producto.
     */
    public String detailsProduct() {
        return String.format("ID: %s, Nombre: %s, Categoria: %s, Stock %d",
                             product.getId(),
                             product.getName().getName(),
                             product.getCategory(),
                             product.getStock().getAmount());
    }

}

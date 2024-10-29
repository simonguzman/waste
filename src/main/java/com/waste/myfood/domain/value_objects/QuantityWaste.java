package com.waste.myfood.domain.value_objects;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuantityWaste {
    private String id;
    private double wasteQuantity;
    private double totalWasteQuantity;

    public QuantityWaste(double wasteQuantity) {
        this.id = UUID.randomUUID().toString();
        this.wasteQuantity = wasteQuantity;
        this.totalWasteQuantity = wasteQuantity;
    }

    /**
     * Agrega una cantidad de desperdicio a la cantidad total registrada.
     *
     * @param quantity La cantidad de desperdicio a agregar.
     * @return true si la cantidad fue agregada correctamente, false si la cantidad es menor o igual a cero.
     */
    public boolean addQuantity(double quantity) {
        if (quantity <= 0)
            return false;
        this.wasteQuantity += quantity;
        this.totalWasteQuantity += quantity;
        return true;
    }

    /**
     * Valida si la cantidad de desperdicio inicial es positiva.
     *
     * @return true si la cantidad de desperdicio es mayor que cero, false en caso contrario.
     */
    public boolean validatePositive() {
        return this.wasteQuantity > 0;
    }

    /**
     * Reduce la cantidad total de desperdicio registrada.
     *
     * @param quantity La cantidad de desperdicio a reducir.
     * @return true si la reducción fue exitosa, false si la cantidad es negativa
     * o si la cantidad total resultante sería negativa.
     */
    public boolean reduceQuantity(double quantity) {
        if (quantity < 0 || this.totalWasteQuantity - quantity < 0) {
            return false;
        }
        this.totalWasteQuantity -= quantity;
        return true;
    }

    /**
     * Verifica si la cantidad total de desperdicio registrada es positiva.
     *
     * @return true si la cantidad total de desperdicio es mayor que cero, false en caso contrario.
     */
    public boolean isPositive() {
        return this.totalWasteQuantity > 0;
    }
}

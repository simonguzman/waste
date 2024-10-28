package com.waste.myfood.domain.value_objects;

import java.util.UUID;

import com.waste.myfood.domain.constants.CauseWasteConstants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CauseWaste {
    private String id;
    private String description;

    public CauseWaste(int causeIndex){
        this.id = UUID.randomUUID().toString();
        this.description = this.selectCause(causeIndex);
    }

    /**
     * Selecciona la descripción de la causa de desperdicio según el índice proporcionado.
     *
     * @param causeIndex El índice que representa la causa de desperdicio.
     * @return La descripción de la causa correspondiente al índice.
     */
    public String selectCause(int causeIndex) {
        return switch (causeIndex) {
            case 1 -> CauseWasteConstants.CAUSE_WASTE_EXPIRED;
            case 2 -> CauseWasteConstants.CAUSE_WASTE_EXCESS_PREPARATION;
            case 3 -> CauseWasteConstants.CAUSE_WASTE_BAD_STORAGE;
            case 4 -> CauseWasteConstants.CAUSE_WASTE_PREPARATION_ERROR;
            case 5 -> CauseWasteConstants.CAUSE_WASTE_CUSTOMER_RETURN;
            case 6 -> CauseWasteConstants.CAUSE_WASTE_TRANSPORTER_DAMAGE;
            case 7 -> CauseWasteConstants.CAUSE_WASTE_WEATHER_CONDITIONS;
            case 8 -> CauseWasteConstants.CAUSE_WASTE_HANDLING_DAMAGE;
            case 9 -> CauseWasteConstants.CAUSE_WASTE_DEFECTIVE_INGREDIENTS;
            case 10 -> CauseWasteConstants.CAUSE_WASTE_EXCESS_PORTIONS;
            case 11 -> CauseWasteConstants.CAUSE_WASTE_CANCELED_ORDER;
            case 12 -> CauseWasteConstants.CAUSE_WASTE_CROSS_CONTAMINATION;
            default -> CauseWasteConstants.CAUSE_WASTE_ERROR;
        };
    }

    /**
     * Verifica si la causa de desperdicio proporcionada es válida.
     *
     * @param cause La descripción de la causa a validar.
     * @return true si la causa es válida; false en caso contrario.
     */
    public boolean isValidCause(String cause) {
        return switch (cause) {
            case CauseWasteConstants.CAUSE_WASTE_EXPIRED,
                 CauseWasteConstants.CAUSE_WASTE_EXCESS_PREPARATION,
                 CauseWasteConstants.CAUSE_WASTE_BAD_STORAGE,
                 CauseWasteConstants.CAUSE_WASTE_PREPARATION_ERROR,
                 CauseWasteConstants.CAUSE_WASTE_CUSTOMER_RETURN,
                 CauseWasteConstants.CAUSE_WASTE_TRANSPORTER_DAMAGE,
                 CauseWasteConstants.CAUSE_WASTE_WEATHER_CONDITIONS,
                 CauseWasteConstants.CAUSE_WASTE_HANDLING_DAMAGE,
                 CauseWasteConstants.CAUSE_WASTE_DEFECTIVE_INGREDIENTS,
                 CauseWasteConstants.CAUSE_WASTE_EXCESS_PORTIONS,
                 CauseWasteConstants.CAUSE_WASTE_CANCELED_ORDER,
                 CauseWasteConstants.CAUSE_WASTE_CROSS_CONTAMINATION, 
                 CauseWasteConstants.CAUSE_WASTE_ERROR -> true;
            default -> false;
        };
    } 
}

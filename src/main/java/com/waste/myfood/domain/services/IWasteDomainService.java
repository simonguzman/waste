package com.waste.myfood.domain.services;

import com.waste.myfood.domain.agregates.Waste;
import com.waste.myfood.domain.value_objects.CauseWaste;
import com.waste.myfood.domain.value_objects.ProductWaste;

/**
 * Interfaz que define los servicios del dominio relacionados con la gestión de desperdicio.
 */
public interface  IWasteDomainService {
     
    /**
     * Registra un desperdicio en el sistema.
     *
     * @param waste El objeto Waste que representa el desperdicio a registrar.
     * @return true si el desperdicio se registró correctamente, false en caso contrario.
     */
    public boolean registerWaste(Waste waste);

    /**
     * Sugiere medidas para la reducción del desperdicio basado en el objeto Waste proporcionado.
     *
     * @param waste El objeto Waste para el cual se sugieren medidas de reducción.
     * @return Una cadena que contiene las sugerencias para la reducción del desperdicio.
     */
    public String suggestReductionMeasures(Waste waste);

    /**
     * Aumenta la cantidad de desperdicio en el objeto Waste especificado.
     *
     * @param waste El objeto Waste al cual se le aumentará la cantidad.
     * @param quantity La cantidad a añadir al desperdicio.
     * @return true si la cantidad se aumentó correctamente, false en caso contrario.
     */
    public boolean addQuantity(Waste waste, double quantity);

    /**
     * Reduce la cantidad de desperdicio en el objeto Waste especificado.
     *
     * @param waste El objeto Waste al cual se le reducirá la cantidad.
     * @param quantity La cantidad a reducir del desperdicio.
     * @return true si la cantidad se redujo correctamente, false en caso contrario.
     */
    public boolean reduceQuantity(Waste waste, double quantity);

    /**
     * Obtiene los detalles de un producto basado en el objeto ProductWaste proporcionado.
     *
     * @param product El objeto ProductWaste que representa el producto.
     * @return Una cadena que contiene los detalles del producto.
     */
    public String getDetailsProduct(ProductWaste product);

    /**
     * Selecciona una causa de desperdicio de una lista basada en un índice proporcionado.
     *
     * @param cause El objeto CauseWaste que representa la causa.
     * @param causeIndex El índice de la causa seleccionada.
     * @return Una cadena que representa la causa seleccionada.
     */
    public String selectCause(CauseWaste cause, int causeIndex);

    /**
     * Valida si una causa de desperdicio es válida.
     *
     * @param cause El objeto CauseWaste que representa la causa a validar.
     * @return true si la causa es válida, false en caso contrario.
     */
    public boolean isValidCause(CauseWaste cause);
}

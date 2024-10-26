package com.waste.myfood.application.input;

import java.util.List;

import com.waste.myfood.domain.agregates.Waste;

public interface ManageWasteCUIntPort {
    /**
     * Crea un nuevo registro de desperdicio.
     *
     * @param waste El objeto Waste que se va a crear.
     * @return El objeto Waste persistido o null en caso de un error.
     */
    Waste createWaste(Waste waste);

    /**
     * Actualiza la información de un registro de desperdicio existente.
     *
     * @param waste El objeto Waste que contiene la nueva información. 
     *              Este objeto debe tener un identificador válido que corresponda a un registro existente.
     * @return El objeto Waste actualizado, o null si la actualización falla.
     */
    Waste update(Waste waste);

    /**
     * Registra una cantidad adicional de desperdicio para un registro existente.
     *
     * @param wasteId Identificador del desperdicio a actualizar.
     * @param quantity Cantidad de desperdicio a registrar.
     * @return El objeto Waste actualizado o null en caso de un error.
     */
    Waste registerAdditionalWaste(String wasteId, double quantity);

    /**
     * Recupera todos los registros de desperdicio.
     *
     * @return Una lista de objetos Waste o una lista vacía si no hay desperdicio registrado.
     */
    List<Waste> getAllWastes();

    /**
     * Recupera todos los registros de desperdicio relacionados con una causa específica.
     *
     * @param cause La causa de desperdicio para la cual se desean obtener los registros.
     * @return Una lista de objetos Waste que representan los registros de desperdicio 
     *         asociados con la causa especificada. Si no hay registros, se devuelve una lista vacía.
     */
    List<Waste> getWasteByCause(String cause);

    /**
     * Recupera un registro de desperdicio específico por su identificador.
     *
     * @param wasteId Identificador del desperdicio a recuperar.
     * @return El objeto Waste correspondiente o null si no se encuentra.
     */
    Waste getWasteById(String wasteId);

    /**
     * Recupera todos los registros de desperdicio relacionados con un producto específico.
     *
     * @param productId Identificador del producto para el cual se desean obtener los registros de desperdicio.
     * @return Una lista de objetos Waste que representan los registros de desperdicio 
     *         relacionados con el producto especificado. Si no hay registros, se devuelve una lista vacía.
     */
    List<Waste> getWasteByProductId(String productId);

    /**
     * Calcula la cantidad total de desperdicio registrada para un producto específico.
     *
     * @param productId Identificador del producto para el cual se desea calcular la cantidad total de desperdicio.
     * @return La cantidad total de desperdicio registrada para el producto especificado. 
     *         Si no hay desperdicio registrado, se devuelve 0.
     */
    double getTotalWasteByProductId(String productId);

    /**
     * Sugiere medidas de reducción de desperdicio para un registro específico.
     *
     * @param wasteId Identificador del desperdicio para el cual se desean sugerencias.
     * @return Sugerencias de reducción de desperdicio como una cadena.
     */
    String suggestReductionMeasures(String wasteId);
}

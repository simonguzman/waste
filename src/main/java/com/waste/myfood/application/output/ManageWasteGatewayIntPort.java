package com.waste.myfood.application.output;

import java.util.List;

import com.waste.myfood.domain.agregates.Waste;

public interface ManageWasteGatewayIntPort {
    
    /**
     * Método que permite guardar la información de un registro de desperdicio
     * o actualizarlo en caso de que ya esté registrado.
     * 
     * @param waste información a guardar.
     * @return {@code Waste} instancia del registro de desperdicio almacenado en
     *         la base de datos o {@code null} en caso de error.
     */
    Waste saveWaste(Waste waste);

    /**
     * Recupera la información de todos los registros de desperdicio.
     * 
     * @return {@code List<Waste>} Lista con la información de los registros
     *         de desperdicio en caso de que se encuentren o {@code null}
     *         en caso de que no haya registros.
     */
    List<Waste> findAll();

    /**
     * Recupera un registro de desperdicio específico por su identificador.
     * 
     * @param wasteId Identificador del desperdicio a recuperar.
     * @return {@code Waste} el objeto Waste correspondiente o {@code null} si no se encuentra.
     */
    Waste findById(String wasteId);

    /**
     * Recupera todos los registros de desperdicio relacionados con un producto específico.
     * 
     * @param productId Identificador del producto para el cual se desean obtener los registros de desperdicio.
     * @return {@code List<Waste>} Lista de objetos Waste que representan los registros de desperdicio
     *         relacionados con el producto especificado. Si no hay registros, se devuelve una lista vacía.
     */
    List<Waste> findByProductId(String productId);

    /**
     * Recupera todos los registros de desperdicio relacionados con una causa específica.
     * 
     * @param cause La causa de desperdicio para la cual se desean obtener los registros.
     * @return {@code List<Waste>} Lista de objetos Waste que representan los registros de desperdicio 
     *         asociados con la causa especificada. Si no hay registros, se devuelve una lista vacía.
     */
    List<Waste> findByCause(String cause);

    /**
     * Calcula la cantidad total de desperdicio registrada para un producto específico.
     * 
     * @param productId Identificador del producto para el cual se desea calcular la cantidad total de desperdicio.
     * @return {@code double} La cantidad total de desperdicio registrada para el producto especificado. 
     *         Si no hay desperdicio registrado, se devuelve 0.
     */
    double calculateTotalWasteByProductId(String productId);
}

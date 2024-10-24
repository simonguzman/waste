package com.waste.myfood.domain.constants;

import java.util.HashMap;
import java.util.Map;

public class CauseWasteConstants {
    public static final String CAUSE_WASTE_EXPIRED = "Expired";
    public static final String CAUSE_WASTE_EXCESS_PREPARATION = "Excess preparation";
    public static final String CAUSE_WASTE_BAD_STORAGE = "Bad storage";
    public static final String CAUSE_WASTE_PREPARATION_ERROR = "Preparation error";
    public static final String CAUSE_WASTE_CUSTOMER_RETURN = "Custom return";
    public static final String CAUSE_WASTE_TRANSPORTER_DAMAGE = "Transporter damage";
    public static final String CAUSE_WASTE_WEATHER_CONDITIONS = "Weather conditions";
    public static final String CAUSE_WASTE_HANDLING_DAMAGE = "Handling damage";
    public static final String CAUSE_WASTE_DEFECTIVE_INGREDIENTS = "Defective ingredients";
    public static final String CAUSE_WASTE_EXCESS_PORTIONS = "Excess portions";
    public static final String CAUSE_WASTE_CANCELED_ORDER = "Canceled order";
    public static final String CAUSE_WASTE_CROSS_CONTAMINATION = "Cross contamination";
    public static final String CAUSE_WASTE_ERROR = "Error";


    public static final Map<String, String> CAUSE_SUGGESTIONS = new HashMap<>();

    static{
        CAUSE_SUGGESTIONS.put(CauseWasteConstants.CAUSE_WASTE_EXPIRED, "Medida sugerida: Mejora la rotación de inventario y revisa fechas de vencimiento.");
        CAUSE_SUGGESTIONS.put(CauseWasteConstants.CAUSE_WASTE_BAD_STORAGE, "Medida sugerida: Revisa las condiciones de almacenamiento.");
        CAUSE_SUGGESTIONS.put(CauseWasteConstants.CAUSE_WASTE_EXCESS_PREPARATION, "Medida sugerida: Ajusta las cantidades en la preparación de alimentos.");
        CAUSE_SUGGESTIONS.put(CauseWasteConstants.CAUSE_WASTE_PREPARATION_ERROR, "Medida sugerida: Revisa los procedimientos de preparación para evitar errores.");
        CAUSE_SUGGESTIONS.put(CauseWasteConstants.CAUSE_WASTE_CUSTOMER_RETURN, "Medida sugerida: Analiza las razones de las devoluciones de clientes y ajusta los procesos.");
        CAUSE_SUGGESTIONS.put(CauseWasteConstants.CAUSE_WASTE_TRANSPORTER_DAMAGE, "Medida sugerida: Mejora las prácticas de transporte para evitar daños.");
        CAUSE_SUGGESTIONS.put(CauseWasteConstants.CAUSE_WASTE_WEATHER_CONDITIONS, "Medida sugerida: Ajusta el almacenamiento para adaptarse a las condiciones climáticas.");
        CAUSE_SUGGESTIONS.put(CauseWasteConstants.CAUSE_WASTE_HANDLING_DAMAGE, "Medida sugerida: Capacita al personal para manejar los productos con cuidado.");
        CAUSE_SUGGESTIONS.put(CauseWasteConstants.CAUSE_WASTE_DEFECTIVE_INGREDIENTS, "Medida sugerida: Revisa y controla la calidad de los ingredientes.");
        CAUSE_SUGGESTIONS.put(CauseWasteConstants.CAUSE_WASTE_EXCESS_PORTIONS, "Medida sugerida: Ajusta las porciones para evitar el exceso.");
        CAUSE_SUGGESTIONS.put(CauseWasteConstants.CAUSE_WASTE_CANCELED_ORDER, "Medida sugerida: Revisa los procedimientos de gestión de pedidos para evitar cancelaciones.");
        CAUSE_SUGGESTIONS.put(CauseWasteConstants.CAUSE_WASTE_CROSS_CONTAMINATION, "Medida sugerida: Implementa medidas de seguridad alimentaria para evitar la contaminación cruzada.");
        CAUSE_SUGGESTIONS.put(CauseWasteConstants.CAUSE_WASTE_ERROR, "Medida sugerida: Revisa y corrige los errores en los procesos de manejo de alimentos.");
    }
}

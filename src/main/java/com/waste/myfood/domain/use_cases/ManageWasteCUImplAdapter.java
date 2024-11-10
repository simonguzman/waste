package com.waste.myfood.domain.use_cases;

import java.util.ArrayList;
import java.util.List;

import com.waste.myfood.application.input.ManageWasteCUIntPort;
import com.waste.myfood.application.output.ExceptionFormatterIntPort;
import com.waste.myfood.application.output.ManageCauseGatewayIntPort;
import com.waste.myfood.application.output.ManageProductGatewayIntPort;
import com.waste.myfood.application.output.ManageQuantityGatewayIntPort;
import com.waste.myfood.application.output.ManageWasteGatewayIntPort;
import com.waste.myfood.domain.agregates.Waste;
import com.waste.myfood.domain.constants.CauseWasteConstants;
import com.waste.myfood.domain.value_objects.CauseWaste;
import com.waste.myfood.domain.value_objects.ProductWaste;
import com.waste.myfood.domain.value_objects.QuantityWaste;

public class ManageWasteCUImplAdapter implements ManageWasteCUIntPort {

    private final ManageWasteGatewayIntPort gateway;
    private final ExceptionFormatterIntPort formatter;
    private final ManageProductGatewayIntPort productGateway;
    private final ManageCauseGatewayIntPort causeGateway;
    private final ManageQuantityGatewayIntPort quantityGateway;

    public ManageWasteCUImplAdapter(ManageWasteGatewayIntPort gateway, ExceptionFormatterIntPort formatter,
            ManageProductGatewayIntPort productGateway, ManageCauseGatewayIntPort causeGateway,
            ManageQuantityGatewayIntPort quantityGateway) {
        this.gateway = gateway;
        this.formatter = formatter;
        this.productGateway = productGateway;
        this.causeGateway = causeGateway;
        this.quantityGateway = quantityGateway;
    }

    @Override
    public Waste createWaste(Waste waste) {
        System.out.println("Iniciando creación de Waste: " + waste);
        if (waste.getProduct() == null) {
            System.out.println("Error: Producto no válido");
            this.formatter.returnResponseBusinessRuleViolated("Product can't be null or empty.");
        }
        if (waste.getQuantityWaste() == null) {
            System.out.println("Error: Cantidad de desperdicio no válida");
            this.formatter.returnResponseBusinessRuleViolated("Quantity waste can't be null or empty.");
        }
        if (waste.getCause() == null) {
            System.out.println("Error: Causa de desperdicio no válida");
            this.formatter.returnResponseBusinessRuleViolated("Cause can't be null or empty.");
        }
        System.out.println("Waste validado, procediendo a guardar");
        Waste savedWaste = gateway.saveWaste(waste);
        if (savedWaste == null) {
            this.formatter
                    .returnResponseBusinessRuleViolated("Failed to save waste. Please check the product details.");
        }
        System.out.println("Waste guardado: " + savedWaste);
        return savedWaste;
    }

    @Override
    public Waste update(Waste waste) {
        Waste existingWaste = this.gateway.findById(waste.getIdWaste());
        if (existingWaste == null) {
            this.formatter.returnResponseErrorEntityNotFound(
                    "The waste with id: " + waste.getIdWaste() + " is not registered.");
        }

        // Validaciones básicas
        if (!waste.isValidProduct()) {
            this.formatter.returnResponseBusinessRuleViolated("Product can't be empty.");
        }
        if (waste.getProduct() == null) {
            this.formatter.returnResponseBusinessRuleViolated("Product can't be null.");
        }
        if (!waste.isValidQuantity()) {
            this.formatter.returnResponseBusinessRuleViolated("Quantity can't be empty.");
        }

        try {
            // Actualizamos los campos del waste existente
            existingWaste.updateProduct(waste.getProduct());
            existingWaste.updateQuantityWaste(waste.getQuantityWaste());
            existingWaste.updateCause(waste.getCause());
            existingWaste.updateDateRegister(waste.getDateRegister());

            // Guardamos los cambios
            return this.gateway.saveWaste(existingWaste);
        } catch (Exception e) {
            this.formatter.returnResponseBusinessRuleViolated("Error updating waste: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Waste registerAdditionalWaste(String wasteId, double quantity) {
        if (quantity <= 0) {
            this.formatter.returnResponseBusinessRuleViolated("Quantity must be greater than zero.");
            return null;
        }

        Waste existingWaste = this.gateway.findById(wasteId);
        if (existingWaste == null) {
            this.formatter.returnResponseErrorEntityNotFound("Waste record not found for id: " + wasteId);
            return null;
        }

        try {
            existingWaste.registerWaste(quantity);
            return this.gateway.saveWaste(existingWaste);
        } catch (Exception e) {
            this.formatter.returnResponseBusinessRuleViolated("Error registering waste: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Waste> getAllWastes() {
        List<Waste> wastes = this.gateway.findAll();
        if (wastes == null || wastes.isEmpty())
            this.formatter.returnNoData("No waste records found.");
        return wastes;
    }

    @Override
    public List<Waste> getWasteByCause(String cause) {
        List<Waste> wastes = this.gateway.findByCause(cause);
        if (wastes == null || !wastes.isEmpty())
            this.formatter.returnNoData("No waste records found for cause: " + cause);
        return wastes;
    }

    @Override
    public Waste getWasteById(String wasteId) {
        Waste waste = this.gateway.findById(wasteId);
        if (waste == null)
            this.formatter.returnResponseErrorEntityNotFound("No waste record found for id: " + wasteId);
        return waste;
    }

    @Override
    public List<Waste> getWasteByProductId(String productId) {
        List<Waste> wastes = this.gateway.findByProductId(productId);
        if (wastes == null || wastes.isEmpty()) {
            this.formatter.returnNoData("No se encontraron registros de desperdicio para el producto ID: " + productId);
        }
        // Ordenar por fecha de registro, del más reciente al más antiguo
        wastes.sort((w1, w2) -> w2.getDateRegister().compareTo(w1.getDateRegister()));
        return wastes;
    }

    @Override
    public double getTotalWasteByProductId(String productId) {
        List<Waste> wastes = this.gateway.findByProductId(productId);
        if (wastes == null || wastes.isEmpty()) {
            this.formatter.returnNoData("No se encontraron registros de desperdicio para el producto ID: " + productId);
            return 0.0;
        }
        return wastes.stream()
                .mapToDouble(waste -> waste.getQuantityWaste().getTotalWasteQuantity())
                .sum();
    }

    @Override
    public String suggestReductionMeasures(String wasteId) {
        Waste waste = this.gateway.findById(wasteId);
        if (waste == null)
            this.formatter.returnResponseErrorEntityNotFound("No waste record found for id: " + wasteId);
        return waste.suggestReductionMeasures();
    }

    @Override
    public List<Waste> registerExpired(List<ProductWaste> expired) {
        List<Waste> toRegister = new ArrayList<>();
        List<CauseWaste> causes = this.causeGateway.findByName(CauseWasteConstants.CAUSE_WASTE_EXPIRED);
        CauseWaste cause = null;
        if (causes.isEmpty())
            cause = this.causeGateway.save(new CauseWaste(CauseWasteConstants.CAUSE_WASTE_EXPIRED));
        else
            cause = causes.get(0);
        for (ProductWaste value : expired) {
            if (!value.isValid())
                this.formatter
                        .returnResponseBusinessRuleViolated("The product " + value.detailsProduct() + " is not valid.");
            QuantityWaste amount = this.quantityGateway.save(new QuantityWaste(value.getStock()));
            if (!this.productGateway.existById(value.getId()))
                value = this.productGateway.save(value);
            else
                value = this.productGateway.findById(value.getId());
            toRegister.add(new Waste(value, amount, cause));
        }
        return this.gateway.saveAll(toRegister);
    }

}

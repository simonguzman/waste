package com.waste.myfood.domain.value_objects;

import java.util.Date;
import java.util.UUID;

import com.waste.myfood.domain.agregates.Waste;

import lombok.Getter;

@Getter
public class WasteReport {
    private String idReport;
    private String productId;
    private double totalWasteQuantity;
    private String causeDescription;
    private Date dateRegister;

    public WasteReport(Waste waste) {
        this.idReport = UUID.randomUUID().toString();
        this.productId = waste.getProductId();
        this.totalWasteQuantity = waste.getQuantityWaste().getTotalWasteQuantity();
        this.causeDescription = waste.getCause().getDescription();
        this.dateRegister = new Date();
    }

    public String getDetailsReport() {
        return String.format("ID Reporte: %s, ID Producto: %s, Cantidad Desperdiciada: %.2f, Causa: %s, Fecha Registro: %s",
                idReport, productId, totalWasteQuantity, causeDescription, dateRegister);
    }
}

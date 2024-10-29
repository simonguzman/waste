package com.waste.myfood.infrastructure.output.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "QuantityWaste")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuantityWasteEntity {
    
    @Id
    @Column(name = "idQuantityWaste", updatable = false, nullable = false)
    private String idQuantityWaste;

    @Column(name = "wasteQuantity", nullable = false)
    private double wasteQuantity;

    @Column(name = "totalWasteQuantity", nullable = false)
    private double totalWasteQuantity;
}

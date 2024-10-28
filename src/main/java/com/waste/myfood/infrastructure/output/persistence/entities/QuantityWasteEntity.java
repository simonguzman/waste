package com.waste.myfood.infrastructure.output.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "QuantityWaste")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuantityWasteEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idQuantityWaste", updatable = false, nullable = false)
    private String idQuantityWaste;

    @Column(name = "wasteQuantity", nullable = false)
    private double wasteQuantity;

    @Column(name = "totalWasteQuantity", nullable = false)
    private double totalWasteQuantity;
}

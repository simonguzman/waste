package com.waste.myfood.infrastructure.output.persistence.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Waste")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WasteEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idWaste", updatable = false, nullable = false)
    private String idWaste;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductWasteEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quantityWaste_id", nullable = false)
    private QuantityWasteEntity quantityWaste;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "causeWaste_id")
    private CauseWasteEntity causeWaste;

    @Column(name = "dateRegister", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateRegister;

}

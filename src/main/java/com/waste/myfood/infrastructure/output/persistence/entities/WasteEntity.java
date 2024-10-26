package com.waste.myfood.infrastructure.output.persistence.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @Column(name = "quantityWaste", nullable = false)
    private double quantityWaste;

    @Column(name = "cause", nullable = false, length = 100)
    private String cause;

    @Column(name = "dateRegister", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateRegister;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "waste_id")
    private List<ProductWasteEntity> productsWaste = new ArrayList<>();

}

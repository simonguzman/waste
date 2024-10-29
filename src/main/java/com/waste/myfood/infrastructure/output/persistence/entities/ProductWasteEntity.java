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
@Table(name = "Product_Waste")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductWasteEntity {
   
    @Id
    @Column(name = "id",updatable = false, nullable = false)
    private String id;

    @Column(unique = true, name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "category", nullable = false, length = 50)
    private String category;

    @Column(nullable = false)
    private double stock;
}

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
@Table(name = "CauseWaste")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CauseWasteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idCauseWaste")
    private String idCauseWaste;

    @Column(name = "description", length = 255)
    private String description;

}

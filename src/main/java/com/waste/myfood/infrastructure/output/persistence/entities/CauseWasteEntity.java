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
@Table(name = "CauseWaste")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CauseWasteEntity {
    @Id
    @Column(name = "idCauseWaste")
    private String idCauseWaste;

    @Column(name = "description", length = 255)
    private String description;

}

package com.waste.myfood.infrastructure.output.persistence.repositories;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.waste.myfood.infrastructure.output.persistence.entities.WasteEntity;

public interface WasteRepository extends JpaRepository<WasteEntity, String> {

    List<WasteEntity> findAllByProduct_Id(String productId);

    List<WasteEntity> findAllByCauseWaste_Description(String description);
}

package com.waste.myfood.infrastructure.output.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.waste.myfood.infrastructure.output.persistence.entities.WasteEntity;

public interface WasteRepository extends JpaRepository<WasteEntity, String> {
    
    List<WasteEntity> findAllByProductId(String productId);

    List<WasteEntity> findAllByCause(String cause);

    //double calculateTotalWasteByProduct_Id(String productId);
}

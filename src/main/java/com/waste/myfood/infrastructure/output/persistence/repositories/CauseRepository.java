package com.waste.myfood.infrastructure.output.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.waste.myfood.infrastructure.output.persistence.entities.CauseWasteEntity;

public interface CauseRepository extends JpaRepository<CauseWasteEntity, String> {
    List<CauseWasteEntity> findByDescription(String description);
}

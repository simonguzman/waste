package com.waste.myfood.infrastructure.output.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.waste.myfood.infrastructure.output.persistence.entities.ProductWasteEntity;

public interface ProductRepository extends JpaRepository<ProductWasteEntity, String> {

}

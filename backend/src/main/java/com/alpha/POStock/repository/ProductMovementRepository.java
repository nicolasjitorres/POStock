package com.alpha.POStock.repository;

import com.alpha.POStock.entity.ProductMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductMovementRepository extends JpaRepository<ProductMovement, Long> {
}

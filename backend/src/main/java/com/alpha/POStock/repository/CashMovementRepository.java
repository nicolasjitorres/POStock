package com.alpha.POStock.repository;

import com.alpha.POStock.entity.CashMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashMovementRepository extends JpaRepository<CashMovement, Long> {
}

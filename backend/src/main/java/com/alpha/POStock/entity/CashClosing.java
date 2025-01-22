package com.alpha.POStock.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CashClosing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Double totalSales;
    private Double totalIncome;
    private Double totalExpenses;
    private Double initialAmount;
    private Double finalAmount;
    private Double totalClosure;
    private List<Sale> sales;
    private List<CashMovement> cashMovements;
    private String observations;
}

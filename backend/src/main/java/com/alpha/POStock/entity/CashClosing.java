package com.alpha.POStock.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
    private int totalSales;
    private BigDecimal totalIncome;
    private BigDecimal totalExpenses;
    private BigDecimal initialAmount;
    private BigDecimal finalAmount;
    private BigDecimal totalClosure;
    private String observations;

    @OneToMany(mappedBy = "cash_closing")
    private List<Sale> saleList;

    @OneToMany(mappedBy = "cash_closing")
    private List<CashMovement> cashMovementList;
}

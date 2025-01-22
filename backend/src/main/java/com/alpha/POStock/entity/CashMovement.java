package com.alpha.POStock.entity;

import com.alpha.POStock.entity.enums.TypeCashMovement;
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
public class CashMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal total;
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private TypeCashMovement type;

    @ManyToOne
    @JoinColumn(name = "cash_closing_id")
    private CashClosing cashClosing;
}

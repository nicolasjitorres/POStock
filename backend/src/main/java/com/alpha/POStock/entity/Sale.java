package com.alpha.POStock.entity;

import com.alpha.POStock.entity.enums.TypeSale;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateTime;
    private BigDecimal total;
    private BigDecimal usedBalance;

    @Enumerated(EnumType.STRING)
    private TypeSale type;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleDetail> saleDetailList;

    @ManyToOne
    @JoinColumn(name = "cash_closing_id")
    @JsonIgnore
    private CashClosing cashClosing;
}

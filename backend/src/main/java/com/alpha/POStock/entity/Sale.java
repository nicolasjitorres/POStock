package com.alpha.POStock.entity;

import com.alpha.POStock.entity.enums.TypeSale;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateTime;
    private Double total;
    private TypeSale type;
    private Double usedBalance;
    @ManyToOne
    private User user;
}

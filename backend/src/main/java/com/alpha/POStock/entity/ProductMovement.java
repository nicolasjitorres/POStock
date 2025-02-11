package com.alpha.POStock.entity;

import com.alpha.POStock.entity.enums.TypeProductMovement;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int amount;
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private TypeProductMovement type;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    private Product product;
}

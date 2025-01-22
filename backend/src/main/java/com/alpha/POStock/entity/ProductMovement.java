package com.alpha.POStock.entity;

import com.alpha.POStock.entity.enums.TypeProductMovement;
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
    private Long productId;
    @Enumerated(EnumType.STRING)
    private TypeProductMovement type;
    private int amount;
    private LocalDateTime dateTime;
}

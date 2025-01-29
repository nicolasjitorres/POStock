package com.alpha.POStock.dto;

import com.alpha.POStock.entity.enums.TypeSale;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {
    private LocalDateTime dateTime;
    private BigDecimal total;
    private BigDecimal usedBalance;

    @Enumerated(EnumType.STRING)
    private TypeSale type;

    private Long userId;
    private List<SaleDetailDTO> saleDetailDTOList;
}

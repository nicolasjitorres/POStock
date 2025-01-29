package com.alpha.POStock.service;

import com.alpha.POStock.dto.SaleDTO;
import com.alpha.POStock.dto.SaleDetailDTO;
import com.alpha.POStock.entity.Product;
import com.alpha.POStock.entity.Sale;
import com.alpha.POStock.entity.SaleDetail;
import com.alpha.POStock.entity.User;
import com.alpha.POStock.entity.enums.Role;
import com.alpha.POStock.entity.enums.TypeSale;
import com.alpha.POStock.repository.SaleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SaleServiceTest {

    @Mock
    private SaleRepository saleRepository;

    @Mock
    private UserService userService;

    @Mock
    private ProductService productService;

    @Mock
    private SaleDetailService saleDetailService;

    @InjectMocks
    private SaleService saleService;

    private Sale sale;
    private SaleDTO saleDTO;

    @BeforeEach
    void setUp() {
        saleDTO = new SaleDTO();
        saleDTO.setDateTime(LocalDateTime.now());
        saleDTO.setType(TypeSale.VENTA);
        saleDTO.setTotal(BigDecimal.valueOf(100));
        saleDTO.setUsedBalance(BigDecimal.ZERO);
        saleDTO.setUserId(1L);

        SaleDetailDTO saleDetailDTO = new SaleDetailDTO();
        saleDetailDTO.setProductId(1L);
        saleDetailDTO.setAmount(2);

        saleDTO.setSaleDetailDTOList(Collections.singletonList(saleDetailDTO));

        sale = new Sale();
        sale.setId(1L);
        sale.setDateTime(saleDTO.getDateTime());
        sale.setType(saleDTO.getType());
        sale.setTotal(saleDTO.getTotal());
        sale.setUsedBalance(saleDTO.getUsedBalance());
    }

    @Test
    void createSale(){
        Product product = new Product(1L, "Test", "Test desc", BigDecimal.valueOf(10),"abc123",1,null,null);
        User user = new User(1L, "test", "test@g.com",null, Role.ADMINISTRADOR, null);

        when(productService.getProductById(anyLong())).thenReturn(product);
        when(userService.getUserById(anyLong())).thenReturn(user);
        when(saleRepository.save(any(Sale.class))).thenReturn(sale);

        Sale createdSale = saleService.createSale(1L, saleDTO);

        assertNotNull(createdSale);
        assertEquals(sale.getTotal(), createdSale.getTotal());
        verify(saleRepository, times(1)).save(any(Sale.class));
        verify(saleDetailService, times(1)).createSaleDetail(any(SaleDetail.class));
    }

}

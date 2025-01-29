package com.alpha.POStock.service;

import com.alpha.POStock.entity.Product;
import com.alpha.POStock.entity.ProductMovement;
import com.alpha.POStock.entity.enums.TypeProductMovement;
import com.alpha.POStock.exception.InsufficientStockException;
import com.alpha.POStock.repository.ProductMovementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductMovementServiceTest {

    @Mock
    private ProductMovementRepository productMovementRepository;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductMovementService productMovementService;


    @Test
    void testCreateProductMovementIn(){
        Product product = new Product(1L, "Test", "Test desc", BigDecimal.valueOf(10),"abc123",1,null,null);
        ProductMovement productMovement = new ProductMovement(null,4,null, TypeProductMovement.INGRESO,product);

        when(productService.getProductById(1L)).thenReturn(product);
        when(productService.updateProduct(anyLong(), any(Product.class))).thenReturn(product);
        when(productMovementRepository.save(any(ProductMovement.class))).thenReturn(productMovement);

        ProductMovement result = productMovementService.createProductMovement(1L, productMovement);

        assertNotNull(result);
        assertEquals(5, product.getStock());
        verify(productService).updateProduct(1L, product);
        verify(productMovementRepository).save(productMovement);
    }

    @Test
    void testCreateProductMovementOut(){
        Product product = new Product(1L, "Test", "Test desc", BigDecimal.valueOf(10),"abc123",5,null,null);
        ProductMovement productMovement = new ProductMovement(null, 4, null, TypeProductMovement.EGRESO, product);

        when(productService.getProductById(1L)).thenReturn(product);
        when(productService.updateProduct(anyLong(),any(Product.class))).thenReturn(product);
        when(productMovementRepository.save(any(ProductMovement.class))).thenReturn(productMovement);

        ProductMovement result = productMovementService.createProductMovement(1L, productMovement);

        assertNotNull(result);
        assertEquals(1, product.getStock());
        verify(productService).updateProduct(1L, product);
        verify(productMovementRepository).save(productMovement);
    }

    @Test
    void testCreateProductMovementOutInsufficientStock(){
        Product product = new Product(1L, "Test", "Test desc", BigDecimal.valueOf(10),"abc123",3,null,null);
        ProductMovement productMovement = new ProductMovement(null, 4, null, TypeProductMovement.EGRESO, product);

        when(productService.getProductById(1L)).thenReturn(product);

        InsufficientStockException exception = assertThrows(
                InsufficientStockException.class,
                () -> productMovementService.createProductMovement(1L, productMovement)
        );

        assertEquals("El producto no tiene stock disponible.", exception.getMessage());
        verify(productService, never()).updateProduct(anyLong(), any(Product.class));
        verify(productMovementRepository, never()).save(any(ProductMovement.class));
    }


}

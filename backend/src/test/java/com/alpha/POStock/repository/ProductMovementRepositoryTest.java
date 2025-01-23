package com.alpha.POStock.repository;

import com.alpha.POStock.entity.Product;
import com.alpha.POStock.entity.ProductMovement;
import com.alpha.POStock.entity.enums.TypeProductMovement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductMovementRepositoryTest {

    @Autowired
    private ProductMovementRepository productMovementRepository;

    @Autowired
    private ProductRepository productRepository;

    private ProductMovement productMovement;

    @BeforeEach
    void setUpProductMovement(){
        Product product = new Product();
        product.setName("test");
        product.setDescription("test description");
        product.setPrice(BigDecimal.valueOf(123.45));
        product.setBarCode("abc123");
        product.setStock(5);
        Product createProduct = productRepository.save(product);

        productMovement = new ProductMovement();
        productMovement.setAmount(4);
        productMovement.setType(TypeProductMovement.INGRESO);
        productMovement.setDateTime(LocalDateTime.now());
        productMovement.setProduct(createProduct);
    }

    @Test
    void testCreateProductMovement(){
        ProductMovement createProductMovement = productMovementRepository.save(productMovement);

        assertThat(createProductMovement).isNotNull();
        assertThat(createProductMovement.getAmount()).isEqualTo(4);
    }

    @Test
    void testUpdateProductMovement(){
        ProductMovement createProductMovement = productMovementRepository.save(productMovement);

        createProductMovement.setAmount(10);

        ProductMovement updateProductMovement = productMovementRepository.save(createProductMovement);

        assertThat(updateProductMovement).isNotNull();
        assertThat(updateProductMovement.getAmount()).isEqualTo(10);

    }

    @Test
    void testDeleteProductMovement(){
        ProductMovement createProductMovement = productMovementRepository.save(productMovement);

        productMovementRepository.delete(createProductMovement);

        Optional<ProductMovement> deletedProductMovement = productMovementRepository.findById(createProductMovement.getId());

        assertThat(deletedProductMovement).isNotPresent();
    }

    @Test
    void testFindProductMovementById(){
        ProductMovement createProductMovement = productMovementRepository.save(productMovement);

        Optional<ProductMovement> findProductMovement = productMovementRepository.findById(createProductMovement.getId());

        assertThat(findProductMovement).isPresent();
        assertThat(findProductMovement.get().getAmount()).isEqualTo(4);
    }

}

package com.alpha.POStock.repository;

import com.alpha.POStock.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    void setUpProduct(){
        product = new Product();
        product.setName("test");
        product.setDescription("test description");
        product.setPrice(BigDecimal.valueOf(123.45));
        product.setBarCode("abc123");
        product.setStock(5);
    }

    @Test
    void testCreateProduct(){
        Product createdProduct = productRepository.save(product);

        assertThat(createdProduct).isNotNull();
        assertThat(createdProduct.getId()).isNotNull();
        assertThat(createdProduct.getBarCode()).isEqualTo("abc123");
    }

    @Test
    void testUpdateProduct(){
        Product createdProduct = productRepository.save(product);

        createdProduct.setName("test update");
        createdProduct.setBarCode("123abc");

        Product updatedProduct = productRepository.saveAndFlush(createdProduct);

        assertThat(updatedProduct.getName()).isEqualTo("test update");
        assertThat(updatedProduct.getBarCode()).isEqualTo("123abc");
    }

    @Test
    void testDeleteProduct(){
        Product createdProduct = productRepository.save(product);

        productRepository.delete(createdProduct);

        Optional<Product> deletedProduct = productRepository.findById(createdProduct.getId());
        assertThat(deletedProduct).isNotPresent();
    }

    @Test
    void testFindProductById(){
        Product createdProduct = productRepository.save(product);
        Optional<Product> findProduct = productRepository.findById(createdProduct.getId());

        assertThat(findProduct).isPresent();
        assertThat(findProduct.get().getName()).isEqualTo("test");
    }

    @Test
    void testUFindProductByEmail(){
        productRepository.save(product);

        Optional<Product> productOptional = productRepository.findByBarCode("abc123");

        assertThat(productOptional).isPresent();
        assertThat(productOptional.get().getBarCode()).isEqualTo("abc123");
    }

}

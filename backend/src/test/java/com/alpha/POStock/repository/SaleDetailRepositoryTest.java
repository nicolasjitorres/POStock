package com.alpha.POStock.repository;

import com.alpha.POStock.entity.Product;
import com.alpha.POStock.entity.Sale;
import com.alpha.POStock.entity.SaleDetail;
import com.alpha.POStock.entity.User;
import com.alpha.POStock.entity.enums.Role;
import com.alpha.POStock.entity.enums.TypeSale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class SaleDetailRepositoryTest {

    @Autowired
    private SaleDetailRepository saleDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private UserRepository userRepository;

    private SaleDetail saleDetail;

    @BeforeEach
    void setUpSaleDetail(){
        Product product = new Product();
        product.setName("test");
        product.setDescription("test description");
        product.setPrice(BigDecimal.valueOf(123.45));
        product.setBarCode("abc123");
        product.setStock(5);
        Product createProduct = productRepository.save(product);

        User user = new User();
        user.setName("test");
        user.setEmail("test@gmail.com");
        user.setPassword("hola123");
        user.setRole(Role.ADMINISTRADOR);
        User createUser = userRepository.save(user);

        Sale sale = new Sale();
        sale.setType(TypeSale.VENTA);
        sale.setDateTime(LocalDateTime.now());
        sale.setUsedBalance(BigDecimal.valueOf(0));
        sale.setUser(createUser);
        Sale createSale = saleRepository.save(sale);

        saleDetail = new SaleDetail();
        saleDetail.setAmount(5);
        saleDetail.setUnitPrice(createProduct.getPrice());
        saleDetail.setProduct(createProduct);
        saleDetail.setSale(createSale);
    }

    @Test
    void testCreateSaleDetail(){
        SaleDetail createSaleDetail = saleDetailRepository.save(saleDetail);

        assertThat(createSaleDetail).isNotNull();
        assertThat(createSaleDetail.getUnitPrice()).isEqualTo(BigDecimal.valueOf(123.45));
    }

    @Test
    void testUpdateSaleDetail(){
        SaleDetail createSaleDetail = saleDetailRepository.save(saleDetail);

        createSaleDetail.setAmount(2);
        createSaleDetail.setUnitPrice(BigDecimal.valueOf(111.11));

        SaleDetail updateSaleDetail = saleDetailRepository.save(createSaleDetail);

        assertThat(updateSaleDetail).isNotNull();
        assertThat(updateSaleDetail.getAmount()).isEqualTo(2);
        assertThat(updateSaleDetail.getUnitPrice()).isEqualTo(BigDecimal.valueOf(111.11));
    }

    @Test
    void testDeleteSaleDetail(){
        SaleDetail createSaleDetail = saleDetailRepository.save(saleDetail);

        saleDetailRepository.delete(createSaleDetail);

        Optional<SaleDetail> findSaleDetail = saleDetailRepository.findById(createSaleDetail.getId());

        assertThat(findSaleDetail).isNotPresent();
    }

    @Test
    void testFindSaleDetailById(){
        SaleDetail createSaleDetail = saleDetailRepository.save(saleDetail);

        Optional<SaleDetail> findSaleDetail = saleDetailRepository.findById(createSaleDetail.getId());

        assertThat(findSaleDetail).isPresent();
        assertThat(findSaleDetail.get().getAmount()).isEqualTo(5);
        assertThat(findSaleDetail.get().getUnitPrice()).isEqualTo(BigDecimal.valueOf(123.45));
    }

}

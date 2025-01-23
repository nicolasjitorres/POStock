package com.alpha.POStock.repository;

import com.alpha.POStock.entity.Sale;
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
public class SaleRepositoryTest {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private UserRepository userRepository;

    private Sale sale;

    @BeforeEach
    void setUpSale(){
        User user = new User();
        user.setName("test");
        user.setEmail("test@gmail.com");
        user.setPassword("hola123");
        user.setRole(Role.ADMINISTRADOR);
        User createUser = userRepository.save(user);

        sale = new Sale();
        sale.setType(TypeSale.VENTA);
        sale.setDateTime(LocalDateTime.now());
        sale.setUsedBalance(BigDecimal.valueOf(0));
        sale.setUser(createUser);
    }

    @Test
    void testCreateSale(){
        Sale createSale = saleRepository.save(sale);

        assertThat(createSale).isNotNull();
        assertThat(createSale.getType()).isEqualTo(TypeSale.VENTA);
    }

    @Test
    void testUpdateSale(){
        Sale createSale = saleRepository.save(sale);

        createSale.setType(TypeSale.CAMBIO);

        Sale updateSale = saleRepository.save(createSale);

        assertThat(updateSale).isNotNull();
        assertThat(updateSale.getType()).isEqualTo(TypeSale.CAMBIO);
    }

    @Test
    void testDeleteSale(){
        Sale createSale = saleRepository.save(sale);

        saleRepository.delete(createSale);

        Optional<Sale> findSale = saleRepository.findById(createSale.getId());

        assertThat(findSale).isNotPresent();
    }

    @Test
    void testFindSaleById(){
        Sale createSale = saleRepository.save(sale);

        Optional<Sale> findSale = saleRepository.findById(createSale.getId());

        assertThat(findSale).isPresent();
        assertThat(findSale.get().getType()).isEqualTo(TypeSale.VENTA);
    }

}

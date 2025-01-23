package com.alpha.POStock.repository;

import com.alpha.POStock.entity.CashMovement;
import com.alpha.POStock.entity.enums.TypeCashMovement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CashMovementRepositoryTest {

    @Autowired
    private CashMovementRepository cashMovementRepository;

    private CashMovement cashMovement;

    @BeforeEach
    void setUpCashMovement(){
        cashMovement = new CashMovement();
        cashMovement.setType(TypeCashMovement.EGRESO);
        cashMovement.setTotal(BigDecimal.valueOf(123.45));
        cashMovement.setDescription("test cash movement");
        cashMovement.setDateTime(LocalDateTime.now());
    }

    @Test
    void testCreateCashMovement(){
        CashMovement createCashMovement = cashMovementRepository.save(cashMovement);

        assertThat(createCashMovement).isNotNull();
        assertThat(createCashMovement.getType()).isEqualTo(TypeCashMovement.EGRESO);
        assertThat(createCashMovement.getDescription()).isEqualTo("test cash movement");
    }

    @Test
    void testUpdateCashMovement(){
        CashMovement createCashMovement = cashMovementRepository.save(cashMovement);

        createCashMovement.setType(TypeCashMovement.INGRESO);
        createCashMovement.setDescription("test update");

        CashMovement updateCashMovement = cashMovementRepository.save(createCashMovement);

        assertThat(updateCashMovement).isNotNull();
        assertThat(updateCashMovement.getType()).isEqualTo(TypeCashMovement.INGRESO);
        assertThat(updateCashMovement.getDescription()).isEqualTo("test update");
    }

    @Test
    void testDeleteCashMovement(){
        CashMovement createCashMovement = cashMovementRepository.save(cashMovement);

        cashMovementRepository.delete(createCashMovement);

        Optional<CashMovement> findCashMovement = cashMovementRepository.findById(createCashMovement.getId());

        assertThat(findCashMovement).isNotPresent();
    }

    @Test
    void testFindCashMovementById(){
        CashMovement createCashMovement = cashMovementRepository.save(cashMovement);

        Optional<CashMovement> findCashMovement = cashMovementRepository.findById(createCashMovement.getId());

        assertThat(findCashMovement).isPresent();
        assertThat(findCashMovement.get().getType()).isEqualTo(TypeCashMovement.EGRESO);
        assertThat(findCashMovement.get().getDescription()).isEqualTo("test cash movement");
    }

}

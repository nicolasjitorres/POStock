package com.alpha.POStock.repository;

import com.alpha.POStock.entity.CashClosing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CashClosingRepositoryTest {

    @Autowired
    private CashClosingRepository cashClosingRepository;

    private CashClosing cashClosing;

    @BeforeEach
    void setUpCashClosing(){
        cashClosing = new CashClosing();
        cashClosing.setObservations("test cash closing");
        cashClosing.setStartDateTime(LocalDateTime.now());
        cashClosing.setEndDateTime(LocalDateTime.now());
        cashClosing.setTotalSales(20);
        cashClosing.setInitialAmount(BigDecimal.valueOf(123.45));
        cashClosing.setFinalAmount(BigDecimal.valueOf(678.90));
        cashClosing.setTotalIncome(BigDecimal.valueOf(1.23));
        cashClosing.setTotalExpenses(BigDecimal.valueOf(4.56));
        cashClosing.setTotalClosure(BigDecimal.valueOf(1234.5678));
    }

    @Test
    void testCreateCashClosing(){
        CashClosing createCashClosing = cashClosingRepository.save(cashClosing);

        assertThat(createCashClosing).isNotNull();
        assertThat(createCashClosing.getObservations()).isEqualTo("test cash closing");
        assertThat(createCashClosing.getInitialAmount()).isEqualTo(BigDecimal.valueOf(123.45));
        assertThat(createCashClosing.getTotalClosure()).isEqualTo(BigDecimal.valueOf(1234.5678));
    }

    @Test
    void testUpdateCashClosing(){
        CashClosing createCashClosing = cashClosingRepository.save(cashClosing);

        createCashClosing.setObservations("test update");
        createCashClosing.setInitialAmount(BigDecimal.valueOf(987.65));
        createCashClosing.setTotalClosure(BigDecimal.valueOf(1111.1111));

        CashClosing updateCashClosing = cashClosingRepository.save(createCashClosing);

        assertThat(updateCashClosing).isNotNull();
        assertThat(updateCashClosing.getObservations()).isEqualTo("test update");
        assertThat(updateCashClosing.getInitialAmount()).isEqualTo(BigDecimal.valueOf(987.65));
        assertThat(updateCashClosing.getTotalClosure()).isEqualTo(BigDecimal.valueOf(1111.1111));
    }

    @Test
    void testDeleteCashClosing(){
        CashClosing createCashClosing = cashClosingRepository.save(cashClosing);

        cashClosingRepository.delete(createCashClosing);

        Optional<CashClosing> findCashClosing = cashClosingRepository.findById(createCashClosing.getId());

        assertThat(findCashClosing).isNotPresent();
    }

    @Test
    void testFindCashClosingById(){
        CashClosing createCashClosing = cashClosingRepository.save(cashClosing);

        Optional<CashClosing> findCashClosing = cashClosingRepository.findById(createCashClosing.getId());

        assertThat(findCashClosing).isPresent();
        assertThat(findCashClosing.get().getObservations()).isEqualTo("test cash closing");
        assertThat(findCashClosing.get().getInitialAmount()).isEqualTo(BigDecimal.valueOf(123.45));
        assertThat(findCashClosing.get().getTotalClosure()).isEqualTo(BigDecimal.valueOf(1234.5678));
    }

}

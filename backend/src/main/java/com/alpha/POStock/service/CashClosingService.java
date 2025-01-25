package com.alpha.POStock.service;

import com.alpha.POStock.entity.CashClosing;
import com.alpha.POStock.repository.CashClosingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CashClosingService {

    @Autowired
    private CashClosingRepository cashClosingRepository;

    public CashClosing createCashClosing(CashClosing cashClosing){
        return cashClosingRepository.save(cashClosing);
    }

    public CashClosing updateCashClosing(CashClosing cashClosing){
        return cashClosingRepository.save(cashClosing);
    }

    public void deleteCashClosing(Long id){
        cashClosingRepository.deleteById(id);
    }

    public List<CashClosing> getAllCashClosings(){
        return cashClosingRepository.findAll();
    }

    public Optional<CashClosing> getCashClosingById(Long id){
        return cashClosingRepository.findById(id);
    }

}

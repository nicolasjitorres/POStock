package com.alpha.POStock.service;

import com.alpha.POStock.entity.CashClosing;
import com.alpha.POStock.repository.CashClosingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashClosingService {

    @Autowired
    private CashClosingRepository cashClosingRepository;

    public CashClosing createCashClosing(CashClosing cashClosing){
        return cashClosingRepository.save(cashClosing);
    }

    public CashClosing updateCashClosing(Long id, CashClosing cashClosing){
        CashClosing foundCashClosing = this.getCashClosingById(id);
        foundCashClosing.setObservations(cashClosing.getObservations());
        return cashClosingRepository.save(foundCashClosing);
    }

    public void deleteCashClosing(Long id){
        CashClosing foundCashClosing = this.getCashClosingById(id);
        cashClosingRepository.delete(foundCashClosing);
    }

    public List<CashClosing> getAllCashClosings(){
        return cashClosingRepository.findAll();
    }

    public CashClosing getCashClosingById(Long id){
        return cashClosingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cierre de caja no encontrado."));
    }

}

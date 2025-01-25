package com.alpha.POStock.service;

import com.alpha.POStock.entity.CashMovement;
import com.alpha.POStock.repository.CashMovementRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CashMovementService {

    @Autowired
    private CashMovementRepository cashMovementRepository;

    public CashMovement createCashMovement(CashMovement cashMovement){
        return cashMovementRepository.save(cashMovement);
    }

    public CashMovement updateCashMovement(CashMovement cashMovement){
        return cashMovementRepository.save(cashMovement);
    }

    public void deleteCashMovement(Long id){
        cashMovementRepository.deleteById(id);
    }

    public List<CashMovement> getAllCashMovements(){
        return cashMovementRepository.findAll();
    }

    public CashMovement getCashMovementById(Long id){
        return cashMovementRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Movimiento de caja no encontrado."));
    }

}

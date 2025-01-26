package com.alpha.POStock.service;

import com.alpha.POStock.entity.CashMovement;
import com.alpha.POStock.repository.CashMovementRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashMovementService {

    @Autowired
    private CashMovementRepository cashMovementRepository;

    public CashMovement createCashMovement(CashMovement cashMovement){
        return cashMovementRepository.save(cashMovement);
    }

    public CashMovement updateCashMovement(Long id, CashMovement cashMovement){
        CashMovement foundCashMovement = this.getCashMovementById(id);
        foundCashMovement.setDescription(cashMovement.getDescription());
        return cashMovementRepository.save(foundCashMovement);
    }

    public void deleteCashMovement(Long id){
        CashMovement foundCashMovement = this.getCashMovementById(id);
        cashMovementRepository.delete(foundCashMovement);
    }

    public List<CashMovement> getAllCashMovements(){
        return cashMovementRepository.findAll();
    }

    public CashMovement getCashMovementById(Long id){
        return cashMovementRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Movimiento de caja no encontrado."));
    }

}

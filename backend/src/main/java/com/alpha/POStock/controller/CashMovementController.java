package com.alpha.POStock.controller;

import com.alpha.POStock.entity.CashMovement;
import com.alpha.POStock.service.CashMovementService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cash-movements")
public class CashMovementController {

    @Autowired
    private CashMovementService cashMovementService;

    @GetMapping
    public ResponseEntity<?> getAllCashMovements() {
        return ResponseEntity.ok(cashMovementService.getAllCashMovements());
    }

    @PostMapping
    public ResponseEntity<?> createCashMovement(@RequestBody CashMovement cashMovement) {
        return ResponseEntity.ok(cashMovementService.createCashMovement(cashMovement));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCashMovement(@PathVariable Long id, @RequestBody CashMovement cashMovement) {
        try {
            return ResponseEntity.ok(cashMovementService.updateCashMovement(id, cashMovement));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCashMovement(@PathVariable Long id) {
        try {
            cashMovementService.deleteCashMovement(id);
            return ResponseEntity.ok("Movimiento de caja eliminado correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCashMovementById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(cashMovementService.getCashMovementById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }

}

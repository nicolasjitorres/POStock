package com.alpha.POStock.controller;

import com.alpha.POStock.entity.CashClosing;
import com.alpha.POStock.service.CashClosingService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cash-closings")
public class CashClosingController {

    @Autowired
    private CashClosingService cashClosingService;

    @GetMapping
    public ResponseEntity<?> getAllCashClosings(){
        return ResponseEntity.ok(cashClosingService.getAllCashClosings());
    }

    @PostMapping
    public ResponseEntity<?> createCashClosing(@RequestBody CashClosing cashClosing){
        return ResponseEntity.ok(cashClosingService.createCashClosing(cashClosing));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCashClosing(@PathVariable Long id, @RequestBody CashClosing cashClosing){
        try {
            return ResponseEntity.ok(cashClosingService.updateCashClosing(id, cashClosing));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCashClosing(@PathVariable Long id){
        try {
            cashClosingService.deleteCashClosing(id);
            return ResponseEntity.ok("Cierre de caja eliminado correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCashClosingById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(cashClosingService.getCashClosingById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }

}

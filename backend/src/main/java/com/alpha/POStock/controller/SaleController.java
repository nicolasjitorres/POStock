package com.alpha.POStock.controller;

import com.alpha.POStock.dto.SaleDTO;
import com.alpha.POStock.entity.Sale;
import com.alpha.POStock.service.SaleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping
    public ResponseEntity<?> getAllSales(){
        return ResponseEntity.ok(saleService.getAllSales());
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<?> createSale(@PathVariable Long userId, @RequestBody SaleDTO saleDTO){
        try{
            return ResponseEntity.ok(saleService.createSale(userId, saleDTO));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSale(@PathVariable Long id, @RequestBody Sale sale){
        try{
            return ResponseEntity.ok(saleService.updateSale(id, sale));
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSale(@PathVariable Long id){
        try{
            saleService.deleteSale(id);
            return ResponseEntity.ok("Venta eliminada correctamente.");
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSaleById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(saleService.getSaleById(id));
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }

}

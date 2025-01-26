package com.alpha.POStock.controller;

import com.alpha.POStock.entity.ProductMovement;
import com.alpha.POStock.service.ProductMovementService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product_movements")
public class ProductMovementController {

    @Autowired
    private ProductMovementService productMovementService;

    @GetMapping
    public ResponseEntity<?> getAllProductMovements(){
        return ResponseEntity.ok(productMovementService.getAllProductMovements());
    }

    @PostMapping
    public ResponseEntity<?> createProductMovement(@RequestBody ProductMovement productMovement){
        return ResponseEntity.ok(productMovementService.creteProductMovement(productMovement));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProductMovement(@PathVariable Long id, @RequestBody ProductMovement productMovement){
        try{
            return ResponseEntity.ok(productMovementService.updateProductMovement(id, productMovement));
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductMovement(@PathVariable Long id){
        try{
            productMovementService.deleteProductMovement(id);
            return ResponseEntity.ok("Movimiento de producto eliminado correctamente.");
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductMovementById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(productMovementService.getProductMovementById(id));
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }

}

package com.alpha.POStock.controller;

import com.alpha.POStock.entity.SaleDetail;
import com.alpha.POStock.service.SaleDetailService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sale-details")
public class SaleDetailController {

    @Autowired
    private SaleDetailService saleDetailService;

    @GetMapping
    public ResponseEntity<?> getAllSaleDetails(){
        return ResponseEntity.ok(saleDetailService.getAllSaleDetails());
    }

    @PostMapping("/sale/{saleId}/product/{productId}")
    public ResponseEntity<?> createSaleDetail(@PathVariable Long saleId, @PathVariable Long productId, @RequestBody SaleDetail saleDetail){
        try{
            return ResponseEntity.ok(saleDetailService.createSaleDetail(saleDetail));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{saleDetailId}")
    public ResponseEntity<?> updateSaleDetail(@PathVariable Long saleDetailId, @RequestBody SaleDetail saleDetail){
        try{
            return ResponseEntity.ok(saleDetailService.updateSaleDetail(saleDetailId, saleDetail));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{saleDetailId}")
    public ResponseEntity<?> deleteSaleDetail(@PathVariable Long saleDetailId){
        try{
            saleDetailService.deleteSaleDetail(saleDetailId);
            return ResponseEntity.ok("Detalle de venta eliminado correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{saleDetailId}")
    public ResponseEntity<?> getSaleDetailById(@PathVariable Long saleDetailId){
        try{
            return ResponseEntity.ok(saleDetailService.getSaleDetailById(saleDetailId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }

}

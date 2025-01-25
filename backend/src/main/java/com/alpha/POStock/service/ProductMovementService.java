package com.alpha.POStock.service;

import com.alpha.POStock.entity.ProductMovement;
import com.alpha.POStock.repository.ProductMovementRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductMovementService {

    @Autowired
    private ProductMovementRepository productMovementRepository;

    public ProductMovement creteProductMovement(ProductMovement productMovement){
        return productMovementRepository.save(productMovement);
    }

    public ProductMovement updateProductMovement(ProductMovement productMovement){
        return productMovementRepository.saveAndFlush(productMovement);
    }

    public void deleteProductMovement(Long id){
        productMovementRepository.deleteById(id);
    }

    public List<ProductMovement> getAllProductMovements(){
        return productMovementRepository.findAll();
    }

    public ProductMovement getProductMovementById(Long id){
        return productMovementRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Movimiento de producto no encontrado."));
    }

}

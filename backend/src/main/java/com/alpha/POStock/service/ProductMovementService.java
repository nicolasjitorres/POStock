package com.alpha.POStock.service;

import com.alpha.POStock.entity.ProductMovement;
import com.alpha.POStock.entity.enums.TypeProductMovement;
import com.alpha.POStock.repository.ProductMovementRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductMovementService {

    @Autowired
    private ProductMovementRepository productMovementRepository;

    public ProductMovement creteProductMovement(ProductMovement productMovement){
        return productMovementRepository.save(productMovement);
    }

    public ProductMovement updateProductMovement(Long id, ProductMovement productMovement){
        ProductMovement foundProductMovement = this.getProductMovementById(id);
        foundProductMovement.setType(TypeProductMovement.AJUSTE);

        return productMovementRepository.saveAndFlush(foundProductMovement);
    }

    public void deleteProductMovement(Long id){
        ProductMovement foundProductMovement = this.getProductMovementById(id);
        productMovementRepository.delete(foundProductMovement);
    }

    public List<ProductMovement> getAllProductMovements(){
        return productMovementRepository.findAll();
    }

    public ProductMovement getProductMovementById(Long id){
        return productMovementRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Movimiento de producto no encontrado."));
    }

}

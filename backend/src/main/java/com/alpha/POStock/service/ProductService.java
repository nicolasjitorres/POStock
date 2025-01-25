package com.alpha.POStock.service;

import com.alpha.POStock.entity.Product;
import com.alpha.POStock.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(Product product){
        return productRepository.saveAndFlush(product);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Producto no encontrado."));
    }

    public Product getProductByBarCode(String barCode){
        return productRepository.findByBarCode(barCode).orElseThrow(() -> new EntityNotFoundException("Producto no encontrado."));
    }

}

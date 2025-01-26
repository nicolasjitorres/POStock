package com.alpha.POStock.service;

import com.alpha.POStock.entity.Product;
import com.alpha.POStock.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product){
        Product foundProduct = this.getProductById(id);
        foundProduct.setName(product.getName());

        return productRepository.saveAndFlush(foundProduct);
    }

    public void deleteProduct(Long id){
        Product foundProduct = this.getProductById(id);
        productRepository.delete(foundProduct);
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

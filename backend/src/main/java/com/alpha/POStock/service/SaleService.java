package com.alpha.POStock.service;

import com.alpha.POStock.entity.Sale;
import com.alpha.POStock.repository.SaleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    public Sale createSale(Sale sale){
        return saleRepository.save(sale);
    }

    public Sale updateSale(Sale sale){
        return saleRepository.save(sale);
    }

    public void deleteSale(Long id){
        saleRepository.deleteById(id);
    }

    public List<Sale> getAllSales(){
        return saleRepository.findAll();
    }

    public Sale getSaleById(Long id){
        return saleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Venta no encontrada."));
    }

}

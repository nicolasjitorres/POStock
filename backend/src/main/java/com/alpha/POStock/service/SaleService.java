package com.alpha.POStock.service;

import com.alpha.POStock.entity.Sale;
import com.alpha.POStock.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

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

    public Optional<Sale> getSaleById(Long id){
        return saleRepository.findById(id);
    }

}

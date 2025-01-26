package com.alpha.POStock.service;

import com.alpha.POStock.entity.Sale;
import com.alpha.POStock.repository.SaleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private UserService userService;

    public Sale createSale(Long userId, Sale sale){
        sale.setUser(userService.getUserById(userId));
        return saleRepository.save(sale);
    }

    public Sale updateSale(Long id, Sale sale){
        Sale foundSale = this.getSaleById(id);
        foundSale.setUsedBalance(sale.getUsedBalance());
        return saleRepository.save(foundSale);
    }

    public void deleteSale(Long id){
        Sale foundSale = this.getSaleById(id);
        saleRepository.delete(foundSale);
    }

    public List<Sale> getAllSales(){
        return saleRepository.findAll();
    }

    public Sale getSaleById(Long id){
        return saleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Venta no encontrada."));
    }

}

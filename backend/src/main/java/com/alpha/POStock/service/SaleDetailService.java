package com.alpha.POStock.service;

import com.alpha.POStock.entity.Product;
import com.alpha.POStock.entity.Sale;
import com.alpha.POStock.entity.SaleDetail;
import com.alpha.POStock.repository.SaleDetailRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleDetailService {

    @Autowired
    private SaleDetailRepository saleDetailRepository;

    public SaleDetail createSaleDetail(SaleDetail saleDetail){
        return saleDetailRepository.save(saleDetail);
    }

    public SaleDetail updateSaleDetail(Long saleDetailId, SaleDetail saleDetail){
        SaleDetail foundSaleDetail = this.getSaleDetailById(saleDetailId);
        foundSaleDetail.setAmount(saleDetail.getAmount());
        return saleDetailRepository.save(foundSaleDetail);
    }

    public void deleteSaleDetail(Long id){
        SaleDetail foundSaleDetail = this.getSaleDetailById(id);
        saleDetailRepository.delete(foundSaleDetail);
    }

    public List<SaleDetail> getAllSaleDetails(){
        return saleDetailRepository.findAll();
    }

    public SaleDetail getSaleDetailById(Long id){
        return saleDetailRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Detalle de venta no encontrado."));
    }

}

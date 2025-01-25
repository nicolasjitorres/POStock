package com.alpha.POStock.service;

import com.alpha.POStock.entity.SaleDetail;
import com.alpha.POStock.repository.SaleDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class SaleDetailService {

    @Autowired
    private SaleDetailRepository saleDetailRepository;

    public SaleDetail createSaleDetail(SaleDetail saleDetail){
        return saleDetailRepository.save(saleDetail);
    }

    public SaleDetail updateSaleDetail(SaleDetail saleDetail){
        return saleDetailRepository.save(saleDetail);
    }

    public void deleteSaleDetail(Long id){
        saleDetailRepository.deleteById(id);
    }

    public List<SaleDetail> getAllSaleDetails(){
        return saleDetailRepository.findAll();
    }

    public Optional<SaleDetail> getSaleDetailById(Long id){
        return saleDetailRepository.findById(id);
    }

}

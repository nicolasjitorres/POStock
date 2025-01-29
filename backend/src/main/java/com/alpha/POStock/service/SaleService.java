package com.alpha.POStock.service;

import com.alpha.POStock.dto.SaleDTO;
import com.alpha.POStock.dto.SaleDetailDTO;
import com.alpha.POStock.entity.Product;
import com.alpha.POStock.entity.Sale;
import com.alpha.POStock.entity.SaleDetail;
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

    @Autowired
    private ProductService productService;

    @Autowired
    private SaleDetailService saleDetailService;

    public Sale createSale(Long userId, SaleDTO saleDTO){
        Sale sale = new Sale();
        sale.setDateTime(saleDTO.getDateTime());
        sale.setType(saleDTO.getType());
        sale.setTotal(saleDTO.getTotal());
        sale.setUsedBalance(saleDTO.getUsedBalance());
        sale.setUser(userService.getUserById(saleDTO.getUserId()));
        Sale createdSale = saleRepository.save(sale);
        for (SaleDetailDTO saleDetailDTO : saleDTO.getSaleDetailDTOList()){
            Product product = productService.getProductById(saleDetailDTO.getProductId());
            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setSale(createdSale);
            saleDetail.setProduct(product);
            saleDetail.setAmount(saleDetailDTO.getAmount());
            saleDetail.setUnitPrice(product.getPrice());
            saleDetailService.createSaleDetail(saleDetail);
        }
        return createdSale;
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

package com.reto.Banco.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto.Banco.entity.ProductEntity;
import com.reto.Banco.repository.ProductRepository;
import com.reto.Banco.service.ProductSevice;


@Service
public class ProductServicesImplementaion implements ProductSevice {


    @Autowired
    public ProductRepository productRepository;



    @Override
    public void CreateProduct(ProductEntity productEntity) throws Exception {

        productRepository.save(productEntity);
    
    }




    
}

package com.reto.Banco.service.Implementation;

import java.util.Optional;

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



    @Override
    public void update(ProductEntity productEntity) throws Exception {       
        productRepository.save(productEntity);
        
    }



    @Override
    public Optional<ProductEntity> findById(long id) throws Exception {        
        return productRepository.findById(id);
    }




    
}

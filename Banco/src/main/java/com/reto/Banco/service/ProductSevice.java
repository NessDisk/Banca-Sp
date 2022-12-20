package com.reto.Banco.service;

import java.util.Optional;

import com.reto.Banco.entity.ProductEntity;

public interface ProductSevice {

    //find by  id
    public Optional<ProductEntity> findById(long id) throws Exception;
    //find by cliente 
    // create product
    public void CreateProduct(ProductEntity productEntity)  throws Exception;
    public void update(ProductEntity productEntity)  throws Exception;
    // update 

    //find by count 
    
     
    
}

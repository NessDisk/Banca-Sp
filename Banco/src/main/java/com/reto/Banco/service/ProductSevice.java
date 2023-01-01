package com.reto.Banco.service;

import java.util.List;
import java.util.Optional;

import com.reto.Banco.entity.ProductEntity;

public interface ProductSevice {

    //find by  id
    public Optional<ProductEntity> findById(long id) throws Exception;
    //find by cliente 
    public List<ProductEntity> findByclienteId(long clienteId) throws Exception;
    // create product
    public void CreateProduct(ProductEntity productEntity)  throws Exception;
    public void update(ProductEntity productEntity)  throws Exception;
    // update 

    //Delete
    public void deleteById(long id)  throws Exception;
    
     
    
}

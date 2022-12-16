package com.reto.Banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reto.Banco.entity.ProductEntity;

@Repository
public interface ProductRepository extends  JpaRepository< ProductEntity, Long  > {
    
}

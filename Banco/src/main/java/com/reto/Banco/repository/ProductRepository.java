package com.reto.Banco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reto.Banco.entity.ProductEntity;

@Repository
public interface ProductRepository extends  JpaRepository< ProductEntity, Long  > {

    List<ProductEntity> findByclienteId(long clienteId);

    
}

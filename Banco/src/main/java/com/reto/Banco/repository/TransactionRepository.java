package com.reto.Banco.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reto.Banco.entity.TransactionEntity;

@Repository
public interface TransactionRepository extends  JpaRepository< TransactionEntity, Long  > {
    
}

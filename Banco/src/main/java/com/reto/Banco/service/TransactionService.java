package com.reto.Banco.service;

import java.util.List;

import com.reto.Banco.entity.TransactionEntity;

public interface TransactionService {

    //create trasaction
    public void CreateTransaction(TransactionEntity productEntity)  throws Exception;
    // get transaction by id producto

    public List<TransactionEntity> findByclienteId(long clienteId) throws Exception;
    
}

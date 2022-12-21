package com.reto.Banco.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto.Banco.entity.TransactionEntity;
import com.reto.Banco.repository.TransactionRepository;
import com.reto.Banco.service.TransactionService;

@Service
public class TransactionImprementation  implements TransactionService {


    @Autowired
    public TransactionRepository productRepository;

    @Override
    public void CreateTransaction(TransactionEntity transactionEntity) throws Exception {
        // TODO Auto-generated method stub
        productRepository.save(transactionEntity);
    }
    
}

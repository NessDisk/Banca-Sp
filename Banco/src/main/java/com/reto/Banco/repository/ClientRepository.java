package com.reto.Banco.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reto.Banco.entity.ClientTable;

@Repository
public interface ClientRepository extends JpaRepository<ClientTable, Long> {
    
    // ClientTable findByclienteId(long clienteId);

}

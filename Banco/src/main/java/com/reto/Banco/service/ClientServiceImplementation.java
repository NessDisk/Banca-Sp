package com.reto.Banco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto.Banco.entity.ClientTable;
import com.reto.Banco.repository.ClientRepository;

@Service
public class ClientServiceImplementation implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public ClientTable CreateCliente(ClientTable client) {
        // TODO Auto-generated method stub
        return clientRepository.save(client);
    }

    @Override
    public List<ClientTable> GetAllCliente() {
        // TODO Auto-generated method stub
        return clientRepository.findAll();
    }
    
}

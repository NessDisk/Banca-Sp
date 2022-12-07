package com.reto.Banco.service;

import java.util.List;

import com.reto.Banco.entity.ClientTable;

public interface ClientService {

    public ClientTable CreateCliente(ClientTable client);
    public List<ClientTable> GetAllCliente();
    
}

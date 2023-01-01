package com.reto.Banco.service;

import java.util.List;
import java.util.Optional;

import com.reto.Banco.entity.ClientTable;

public interface ClientService {

    public ClientTable CreateCliente(ClientTable client)  throws Exception;
    public List<ClientTable> GetAllCliente()  throws Exception;
    public Optional<ClientTable> GetClienteById(long id)  throws Exception;
    public ClientTable UpdateClient(ClientTable client) throws Exception;
    public void delete(Long id)  throws Exception;

   
    
}

package com.reto.Banco.service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto.Banco.entity.ClientTable;
import com.reto.Banco.repository.ClientRepository;
import com.reto.Banco.service.ClientService;

@Service
public class ClientServiceImplementation implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public ClientTable CreateCliente(ClientTable client)  throws Exception {
        // TODO Auto-generated method stub
        return clientRepository.save(client);
    }

    @Override
    public List<ClientTable> GetAllCliente()  throws Exception {
        // TODO Auto-generated method stub
        return clientRepository.findAll();
    }




    @Override
    public  ClientTable UpdateClient(ClientTable client) throws Exception {
        // TODO Auto-generated method stub
        return clientRepository.save(client);
    }

    // @Override
    // public void delete(Long id) throws Exception {
           
    // }

    @Override
    public void delete(Long id) throws Exception {
        // TODO Auto-generated method stub
        clientRepository.deleteById(id);		
    }
    
}

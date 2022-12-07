package com.reto.Banco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.reto.Banco.entity.ClientTable;
import com.reto.Banco.service.ClientService;

@RestController
@RequestMapping("Client")
public class clienteController {

    @Autowired
    ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientTable>> GetCliente(){

        return new ResponseEntity<>(clientService.GetAllCliente(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<ClientTable> createClient(@RequestBody ClientTable client){

        return new ResponseEntity<>(clientService.CreateCliente(client), HttpStatus.CREATED);
        
    }

    
    
}

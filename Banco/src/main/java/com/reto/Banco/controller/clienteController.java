package com.reto.Banco.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reto.Banco.dto.GeneralResponse;
import com.reto.Banco.entity.ClientTable;
import com.reto.Banco.service.ClientService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("Client")
public class clienteController {

    @Autowired
    ClientService clientService;

    /**
     * @return
     */
    @GetMapping
    public ResponseEntity<GeneralResponse<List<ClientTable>>> GetCliente() {
        GeneralResponse<List<ClientTable>> respuesta = new GeneralResponse<>();
		List<ClientTable> datos = null;
		String mensaje = null;	
		HttpStatus estadoHttp = null;

        try {		
            datos = clientService.GetAllCliente();
            

            respuesta.setDatos(datos);
			respuesta.setMensaje(mensaje);
			respuesta.setPeticionExitosa(true);	
			estadoHttp = HttpStatus.OK;
            
        }
        catch(Exception e)
        {
            mensaje = "500 Internal Server Error.Contact the administrator";			
			respuesta.setMensaje(mensaje);
			respuesta.setPeticionExitosa(false);
			estadoHttp = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        
        return new ResponseEntity<>( respuesta,estadoHttp );
    }


    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse<Optional<ClientTable>>> GetClientById(@PathVariable("id") long id) {
        GeneralResponse<Optional<ClientTable>> respuesta = new GeneralResponse<>();
		Optional<ClientTable> datos = null;
		String mensaje = null;	
		HttpStatus estadoHttp = null;

        try {		
            datos = clientService.GetClienteById(id);


            respuesta.setDatos(datos);
			respuesta.setMensaje(mensaje);
			respuesta.setPeticionExitosa(true);	
			estadoHttp = HttpStatus.OK;
            
        }
        catch(Exception e)
        {
            mensaje = "500 Internal Server Error.Contact the administrator";			
			respuesta.setMensaje(mensaje);
			respuesta.setPeticionExitosa(false);
			estadoHttp = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        
        return new ResponseEntity<>( respuesta,estadoHttp );
    }

    @PostMapping
    public ResponseEntity<GeneralResponse<ClientTable>> createClient(@RequestBody ClientTable client){
        GeneralResponse<ClientTable> respuesta = new GeneralResponse<>();
        ClientTable datos = null;
		String mensaje = null;	
		HttpStatus estadoHttp = null;    

        try{
            
            //restar edades
            if(client.getAge() > 18 )
            {
                client.setDateCreation(LocalDate.now());
                datos = clientService.CreateCliente(client);
                mensaje = "0 - Customer successfully created";
            }else {
                mensaje ="1 - Customer could not be create, the age ";
				estadoHttp = HttpStatus.OK;
            }

            

            respuesta.setDatos(datos);
            respuesta.setMensaje(mensaje);
            respuesta.setPeticionExitosa(true);
            estadoHttp = HttpStatus.CREATED;
        }catch(Exception e){

            estadoHttp = HttpStatus.INTERNAL_SERVER_ERROR;	
			mensaje = "500 Internal Server Error.Contact the administrator";
			respuesta.setMensaje(mensaje);
			respuesta.setPeticionExitosa(false);

        }

        return new ResponseEntity<>(respuesta,estadoHttp);

        
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<GeneralResponse<ClientTable>> update(@PathVariable("id") long id ,@RequestBody ClientTable client) {       
       
        GeneralResponse<ClientTable> respuesta = new GeneralResponse<>();
        ClientTable datos = null;
		String mensaje = null;	
		HttpStatus estadoHttp = null;    

        try{
            client.setId(id);
            datos = clientService.UpdateClient(client);
            mensaje = "0 - Customer successfully created";

            respuesta.setDatos(datos);
            respuesta.setMensaje(mensaje);
            respuesta.setPeticionExitosa(true);
            estadoHttp = HttpStatus.CREATED;
        }catch(Exception e){

            estadoHttp = HttpStatus.INTERNAL_SERVER_ERROR;	
			mensaje = "500 Internal Server Error.Contact the administrator";
			respuesta.setMensaje(mensaje);
			respuesta.setPeticionExitosa(false);

        }

        return new ResponseEntity<>(respuesta,estadoHttp);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<GeneralResponse<Long>> delete(@PathVariable("id") long id ) {

        GeneralResponse<Long> respuesta = new GeneralResponse<>();        
		String mensaje = null;	
		HttpStatus estadoHttp = null;    

        try{
            // client.setId(id);
            clientService.delete(id);
           
            mensaje = "0 - Customer successfully delete";

            respuesta.setDatos(id);
            respuesta.setMensaje(mensaje);
            respuesta.setPeticionExitosa(true);
            estadoHttp = HttpStatus.CREATED;
        }catch(Exception e){

            estadoHttp = HttpStatus.INTERNAL_SERVER_ERROR;	
			mensaje = "500 Internal Server Error.Contact the administrator";
			respuesta.setMensaje(mensaje);
			respuesta.setPeticionExitosa(false);

        }

        return new ResponseEntity<>(respuesta,estadoHttp);

    }


}

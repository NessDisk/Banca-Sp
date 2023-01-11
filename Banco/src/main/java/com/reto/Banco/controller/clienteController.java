package com.reto.Banco.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties.Retry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.Trigger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reto.Banco.dto.GeneralResponse;
import com.reto.Banco.entity.ClientTable;
import com.reto.Banco.entity.ProductEntity;
import com.reto.Banco.service.ClientService;
import com.reto.Banco.service.ProductSevice;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.Console;
import java.text.BreakIterator;
import java.text.SimpleDateFormat;  

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("Client")
public class clienteController {

    @Autowired
    ClientService clientService;
    @Autowired 
    ProductSevice productService;
    /**
     * @return
     */
    @GetMapping
    public ResponseEntity<GeneralResponse<List<ClientTable>>>  GetCliente() {
        GeneralResponse<List<ClientTable>> respuesta = new GeneralResponse<>();
		List<ClientTable> datos = null;
		String mensaje = null;	
		HttpStatus estadoHttp = null;

        try {		
            // clientService.GetAllCliente();
            // System.out.println("Pointer");
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
        // return ;
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
        ClientTable finalClient = null; 

        try{
            // para testo en PostMan
            // Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(client.getAuxBirthdate());          
            // client.setBirthdate(date1);  
            // System.out.println("Test: "+client.getAuxBirthdate());


            // finalClient = new ClientTable(            client.getId_Type(),
            //                                           client.getIdNum(),
            //                                           client.getFisrtName(), 
            //                                           client.getLastName(), 
            //                                           client.getEmail(), 
            //                                           date1,
            //                                           client.getAuxBirthdate(),
            //                                           "Admin",
            //                                           LocalDate.now(),
            //                                           date1 ,
            //                                           "Admin");


            // Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(client.getAuxBirthdate());          
            // client.setBirthdate(date1);  
            // System.out.println("Test: "+client.getAuxBirthdate());
            // System.out.println(client.getIdNum());

            finalClient = new ClientTable(            client.getId_Type(),
                                                      client.getIdNum(),
                                                      client.getFisrtName(), 
                                                      client.getLastName(), 
                                                      client.getEmail(), 
                                                      client.getBirthdate(),
                                                      client.getUserCreation(),
                                                      LocalDate.now(),
                                                      LocalDate.now(),
                                                      client.getUserUpdate());                                

            
                                                    

                                                      

        //  finalClient.setAuxBirthdate(client.getAuxBirthdate());
            long years = ChronoUnit.YEARS.between( client.getBirthdate(),  LocalDate.now());
           
            System.out.println(years > 17);
            //restar edades
            if (years > 17 )
            {
                // client.setDatecreation(LocalDate.now());
                datos = clientService.CreateCliente(finalClient);
                respuesta.setDatos(finalClient);
                mensaje = "0 - Customer successfully created";
            }else {
                mensaje ="1 - Customer could not be create, the age ";
				estadoHttp = HttpStatus.BAD_REQUEST;
            }
            


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
            // client.setId(id);
            


            ClientTable  finalClient = new ClientTable(client.getId_Type(),
                                                      client.getIdNum(),
                                                      client.getFisrtName(), 
                                                      client.getLastName(), 
                                                      client.getEmail(), 
                                                      client.getBirthdate(),
                                                    //   client.getUserCreation(),
                                                    "test",
                                                    //   client.getDatecreation(),
                                                    LocalDate.now(), 
                                                      LocalDate.now(),
                                                      //client.getUserUpdate()
                                                      "Admin"
                                                      ); 
            
                        System.out.println("Test");
            finalClient.setId(id);

            datos = clientService.UpdateClient(finalClient);
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
        List<ProductEntity> Products; 
        boolean trigger = false;
        
        try{
            
            Products =  productService.findByclienteId(id);
            // System.out.println(Products.size());


            
            
            for(int i = 0 ; i < Products.size()-1; i++ )
            {
                if(Products.get(i).getState().equalsIgnoreCase("cancelled") )
                {
                    // System.out.println(!Products.get(i).getEstado().equalsIgnoreCase("cancelled"));
                    trigger = true;
                    break;
                }
            }
            System.out.println(trigger);

            if( trigger == false)
            {
                clientService.delete(id);
                mensaje = "0 - Customer successfully delete";
                respuesta.setPeticionExitosa(true);
                estadoHttp = HttpStatus.ACCEPTED;
            }else 
            {
                mensaje = "1 - Customer don't be  delete, exist "+ Products.size() + " active product.";
                respuesta.setPeticionExitosa(false);
                estadoHttp = HttpStatus.BAD_REQUEST;
            }



            // client.setId(id);
           

            respuesta.setDatos(id);
            respuesta.setMensaje(mensaje);
        }catch(Exception e){

            estadoHttp = HttpStatus.INTERNAL_SERVER_ERROR;	
			mensaje = "500 Internal Server Error.Contact the administrator";
			respuesta.setMensaje(mensaje);
			respuesta.setPeticionExitosa(false);

        }

        return new ResponseEntity<>(respuesta,estadoHttp);

    }


}

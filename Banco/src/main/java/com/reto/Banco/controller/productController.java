package com.reto.Banco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reto.Banco.dto.GeneralResponse;
import com.reto.Banco.entity.ProductEntity;
import com.reto.Banco.service.ProductSevice;


@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/Product")
public class productController   {

    @Autowired 
     ProductSevice productSevice;

    @PostMapping("/create")
    public ResponseEntity<GeneralResponse<ProductEntity>> PostCreateProduct(@RequestBody ProductEntity productEnitty) {
        GeneralResponse<ProductEntity> respuesta = new GeneralResponse<>();
		ProductEntity datos = null;
		String mensaje = null;	
		HttpStatus estadoHttp = null;

       

        try {		
            datos = productSevice.CreateProduct(productEnitty);


            respuesta.setDatos(datos);
			respuesta.setMensaje(mensaje);
			respuesta.setPeticionExitosa(true);	
			estadoHttp = HttpStatus.OK;
            
        }
        catch(Exception e)
        {
            mensaje = "500 Internal Server Error. Contact the administrator";			
			respuesta.setMensaje(mensaje);
			respuesta.setPeticionExitosa(false);
			estadoHttp = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        
        return new ResponseEntity<>( respuesta,estadoHttp );
    }
    
}

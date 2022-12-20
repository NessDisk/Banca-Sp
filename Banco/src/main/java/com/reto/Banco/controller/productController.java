package com.reto.Banco.controller;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reto.Banco.dto.GeneralResponse;
import com.reto.Banco.entity.ProductEntity;
import com.reto.Banco.service.ProductSevice;

import jakarta.websocket.server.PathParam;


@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/Product")
public class productController   {

    @Autowired 
     ProductSevice productSevice;

    @PostMapping("/create")
    public ResponseEntity<GeneralResponse<Integer>> PostCreateProduct( /*@PathParam("clienteId") long clienteId,*/ @RequestBody ProductEntity productEnitty) {
        GeneralResponse<Integer> respuesta = new GeneralResponse<>();
		Integer datos = null;
		String mensaje = null;	
		HttpStatus estadoHttp = null;

        try {		
                 
                  if(productEnitty.getSaldo() > 0  &&  Tipocuenta.AHORRO.toString().equals(productEnitty.getTipoCuenta().toString())
                  || Tipocuenta.CORRIENTE.toString().equals(productEnitty.getTipoCuenta().toString())
                  )
                  {
                    //generate number count and define estate
                    if(Tipocuenta.CORRIENTE.toString().equals(productEnitty.getTipoCuenta().toString())){
                        productEnitty.setEstado(Estado.Unenabled.toString());
                        productEnitty.setNumeroCuenta(createProductNumber(Tipocuenta.CORRIENTE));                    
                    }
                    else {
                         productEnitty.setEstado(Estado.enabled.toString());
                        productEnitty.setNumeroCuenta(createProductNumber(Tipocuenta.AHORRO));   
                    }


                      productEnitty.setSaldo((double) productEnitty.getSaldo());
                    //  productEnitty.setClienteId(clienteId);
                      
                      productEnitty.setFechaApertura(LocalDate.now());
                      mensaje = "0 - Customer successfully created";
                      productSevice.CreateProduct(productEnitty);
                      datos = 0;
                  }else {
                      mensaje ="1 - Customer could not be create ";
                      estadoHttp = HttpStatus.OK;
                  }

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


    @PutMapping("/Status/{idProduc}/{tipoEstado}")
    public ResponseEntity<GeneralResponse<Optional<ProductEntity>>> cambiarEstado(@PathVariable("tipoEstado") String tipoEstado,
    @PathVariable("idProduc")	Long idProducto) {
		GeneralResponse<Optional<ProductEntity>> respuesta = new GeneralResponse<>();
		Optional<ProductEntity> datos = null;

		String mensaje = null;
		HttpStatus estadoHttp = null;
		try {
			datos = productSevice.findById(idProducto);

			switch (tipoEstado) {
			case "enabled":
				if (!datos.get().getEstado().toLowerCase().equals(Estado.cancelate.toString())) {
					datos.get().setEstado(tipoEstado);
					mensaje = "0 - Account enabled";
					break;
				} else {
					mensaje = "1 - The product cannot be activated, the product was canceled";
				}
				break;
			case "unenabled":
				datos.get().setEstado(tipoEstado);
				mensaje = "0 - Account disabled";
				break;
			case "cancelate":       
            //in case      
				if (datos.get().getSaldo() < 1 && datos.get().getSaldo() >= 0) {
					datos.get().setEstado(tipoEstado);
					mensaje = "0 - Account cancelled";
				} else {
					mensaje = "1 - Account cannot be cancelled, balance must be US$0";
				}
				break;
			}
			productSevice.update(datos.get());
			respuesta.setDatos(datos);
			respuesta.setMensaje(mensaje);
			respuesta.setPeticionExitosa(true);
			estadoHttp = HttpStatus.OK;

		} catch (Exception e) {
			estadoHttp = HttpStatus.INTERNAL_SERVER_ERROR;
			mensaje = "There was an error. Contact the administrator";
			respuesta.setMensaje(mensaje);
			respuesta.setPeticionExitosa(false);
		}

        System.out.println();
		return new ResponseEntity<>(respuesta, estadoHttp);

	}


    String createProductNumber(Tipocuenta tipocuenta ) throws Exception {		

        int min = 10000000;
        int max = 99999999;
        int int_random = (int)Math.floor(Math.random()*(max-min+1)+min);
        String value  = ""; 

        if(Tipocuenta.AHORRO == tipocuenta)
        value = ""+46+""+int_random;

        else if(Tipocuenta.CORRIENTE == tipocuenta)
        value = ""+23+""+int_random;


		return value;
	}




    public enum Tipocuenta
    {
        AHORRO,
        CORRIENTE
    }

    public enum Estado
    {
        enabled,
        Unenabled,
        cancelate
    }
    
}

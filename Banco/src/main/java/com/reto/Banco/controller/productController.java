package com.reto.Banco.controller;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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




@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/Product")
public class productController   {

    @Autowired 
     ProductSevice productSevice;

    @PostMapping("/create")
    public ResponseEntity<GeneralResponse<Integer>> PostCreateProduct(  @RequestBody ProductEntity productEnitty) {
        GeneralResponse<Integer> respuesta = new GeneralResponse<>();
		Integer datos = null;
		String mensaje = null;	
		HttpStatus estadoHttp = null;

        try {		

            if(Tipocuenta.Savings.toString().equals(productEnitty.getTypeAccount().toString())
            || Tipocuenta.checking.toString().equals(productEnitty.getTypeAccount().toString())
            )
            {
            //     //generate number count and define estate
                if(Tipocuenta.checking.toString().equals(productEnitty.getTypeAccount().toString())){
                    productEnitty.setState(State.disenabled.toString());
                    productEnitty.setNumAccont(createProductNumber(Tipocuenta.checking));                    
                }
                else {
                    productEnitty.setState(State.enabled.toString());
                    productEnitty.setNumAccont(createProductNumber(Tipocuenta.Savings));   
                }
                

                      productEnitty.setBalance((double)0);
                      productEnitty.setBalanceAvailable( (double)0);
                      productEnitty.setDateUdpate(LocalDate.now());
                      productEnitty.setUserCreation("ADMIN");
                      productEnitty.setGMF( productEnitty.getExcludeGMF());
                      productEnitty.setDateCreate(LocalDate.now());
                      

                    //  productEnitty.setClienteId(clienteId);
                      
                      mensaje = "0 - Product successfully created";
                      productSevice.CreateProduct(productEnitty);
                      datos = 0;
                  }else {
                      mensaje ="1 - Product could not be create ";
                      estadoHttp = HttpStatus.BAD_REQUEST;
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
    public ResponseEntity<GeneralResponse<Optional<ProductEntity>>> changeStatus(@PathVariable("tipoEstado") String tipoEstado,
    @PathVariable("idProduc")	Long idProducto) {
		GeneralResponse<Optional<ProductEntity>> respuesta = new GeneralResponse<>();
		Optional<ProductEntity> datos = null;

		String mensaje = null;
		HttpStatus estadoHttp = null;
		try {
			datos = productSevice.findById(idProducto);

			switch (tipoEstado) {
			case "enabled":
				if (!datos.get().getState().toLowerCase().equals(State.cancelled.toString())) {
					datos.get().setState(State.disenabled.toString());
					mensaje = "0 - Account disabled";
					break;
				} else {
					mensaje = "1 - The product cannot be activated, the product was canceled";
				}
				break;
			case "disenabled":
				datos.get().setState(State.enabled.toString());
				mensaje = "0 - Account enabled";
				break;
			case "cancelled":       
            //in case      
				if (datos.get().getBalance() < 1 && datos.get().getBalance() >= 0) {
					datos.get().setState("disenabled");
					mensaje = "0 - Account disenabled";
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
    // I need making a other  endpointOnly for cancelate


    @GetMapping("/get/{clienteId}")
	public ResponseEntity<GeneralResponse<List<ProductEntity>>>  getProductByCliente(@PathVariable("clienteId") Long clienteId) {
		GeneralResponse<List<ProductEntity>> respuesta = new GeneralResponse<>();
		List<ProductEntity> datos = null;
		String mensaje = null;
		HttpStatus estadoHttp = null;


        //sort for balance
        List<ProductEntity> productEnabled =  new ArrayList<ProductEntity>() ;
        List<ProductEntity> productDisenabled =  new ArrayList<ProductEntity>() ;
        List<ProductEntity> productCancelled =  new ArrayList<ProductEntity>() ;

        System.out.println(clienteId);
        try{
                    datos = productSevice.findByclienteId(clienteId);
                    List<ProductEntity> aux =  productSevice.findByclienteId(clienteId);   
                	mensaje = "0 - found " + datos.size() + " accounts";

                   	if (datos.isEmpty()) {
            				mensaje = "1 - No registered accounts found";
			                            }
                    
               

                    for(int i = 0; i < datos.size()  ; i++ )
                    {
                        if(datos.get(i).getState().toString().equals("enabled") )
                        { 
                            
                            System.out.println(">>>>>>> test <<<<<<<<");
                            productEnabled.add(datos.get(i));
                        }
                        
                        else if  (datos.get(i).getState().toString().equals("disenabled") )
                            productDisenabled.add(datos.get(i));
                        
                        else if  (datos.get(i).getState().toString().equals("cancelled") )
                             productCancelled.add(datos.get(i));                
                    }
                  

                    productEnabled = bubble(productEnabled);
                    productDisenabled = bubble(productDisenabled);
                    productCancelled = bubble(productCancelled);

                  
                    List<ProductEntity> auxList =  new ArrayList<ProductEntity>();

                    auxList.addAll(productEnabled);
                    auxList.addAll(productDisenabled);
                    auxList.addAll(productCancelled);

                    datos = auxList;
                    respuesta.setDatos(datos);
                    respuesta.setMensaje(mensaje);
                    respuesta.setPeticionExitosa(true);

                    estadoHttp = HttpStatus.OK;
        }catch(Exception e)
        {
                    mensaje = "There was an error. Contact the administrator";
                    respuesta.setMensaje(mensaje);
                    respuesta.setPeticionExitosa(false);
                    estadoHttp = HttpStatus.INTERNAL_SERVER_ERROR;
        }

	
		

		return new ResponseEntity<>(respuesta, estadoHttp);
	}

    String createProductNumber(Tipocuenta tipocuenta ) throws Exception {		

        int min = 10000000;
        int max = 99999999;
        int int_random = (int)Math.floor(Math.random()*(max-min+1)+min);
        String value  = ""; 

        if(Tipocuenta.Savings == tipocuenta)
        value = ""+46+""+int_random;

        else if(Tipocuenta.checking == tipocuenta)
        value = ""+23+""+int_random;


		return value;
	}


    public List<ProductEntity> bubble(List<ProductEntity> listProducts){
        int i, j;
        ProductEntity aux;
        if(listProducts.size() >= 2){
        for(i=0;i<listProducts.size()-1;i++)
             for( j=0;j<listProducts.size()-i-1;j++)
                  if(listProducts.get(j+1).getBalance()>listProducts.get(j).getBalance()){
                    aux= listProducts.get(j+1);
                    listProducts.set(j+1,listProducts.get(j) );
                    listProducts.set(j, aux);
                  }
        }
    return listProducts;
}

    // ----------------------------------------- Delete - cancelate ------------------------------------------

    @DeleteMapping("/delete/{productId}")
	public   ResponseEntity<GeneralResponse<Long>>   DeleteProductById(@PathVariable("productId") Long productId ) {
		GeneralResponse<Long> respuesta = new GeneralResponse<>();
		long datos = 0;
		String mensaje = null;
		HttpStatus estadoHttp = null;
        Optional<ProductEntity> productEntityCheck = null;

        // System.out.println(ProductId);
        try{
            productEntityCheck = productSevice.findById(productId);

            if(productEntityCheck.get().getBalance() < 1){

                // productSevice.deleteById(productId);
                productEntityCheck.get().setState("cancelled");
                productSevice.CreateProduct(productEntityCheck.get());
                mensaje = "0 - Cancelate product id: "+ productId;
                estadoHttp = HttpStatus.OK;
                respuesta.setPeticionExitosa(true);

            } else {
                mensaje = "1 - product id:"+ productId +" need less of 1$ for delete";
                estadoHttp = HttpStatus.ALREADY_REPORTED;
                respuesta.setPeticionExitosa(true);
            }
            

                   
                     datos = 0;
                    respuesta.setDatos(datos);
                    respuesta.setMensaje(mensaje);

        }catch(Exception e)
        {                    mensaje = "There was an error. Contact the administrator";
                    respuesta.setMensaje(mensaje);
                    respuesta.setPeticionExitosa(false);
                    estadoHttp = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        // return ;
		return new ResponseEntity<>(respuesta, estadoHttp);
	}

    // ---------  exempt GMF ------------


    @PutMapping("/exemptgmf/{idClient}")
	public   ResponseEntity<GeneralResponse<Long>>  exemptGMF(@PathVariable("idClient") Long productId ) {

        GeneralResponse<Long> respuesta = new GeneralResponse<>();
		long datos = 0;
		String mensaje = null;
		HttpStatus estadoHttp = null;
        Optional<ProductEntity> productEntityCheck = null;
        List<ProductEntity> Products= null;

        try{            

            productEntityCheck = productSevice.findById(productId);
            Products = productSevice.findByclienteId(productEntityCheck.get().getClienteId());


            
            for(int i = 0; i < Products.size()  ; i++  )
            {
                Products.get(i).setGMF(false);
                productSevice.CreateProduct(Products.get(i));
            }

            productEntityCheck.get().setGMF(true);

            productSevice.CreateProduct(productEntityCheck.get());

            datos = 0;
            mensaje = "0 -  Exempt GMF was marked as successful";
            respuesta.setDatos(datos);
            respuesta.setMensaje(mensaje);
            respuesta.setPeticionExitosa(true);
            estadoHttp = HttpStatus.OK;

        } catch(Exception e){
            mensaje = "There was an error. Contact the administrator";
            respuesta.setMensaje(mensaje);
            respuesta.setPeticionExitosa(false);
            estadoHttp = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(respuesta, estadoHttp);
    }
    



    public enum Tipocuenta
    {
        Savings,
        checking
    }

    public enum State
    {
        enabled,
        disenabled,
        cancelled
    }
    
}

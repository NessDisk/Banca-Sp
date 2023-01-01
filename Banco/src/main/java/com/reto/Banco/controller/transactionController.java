package com.reto.Banco.controller;


import java.time.LocalDate;
import java.util.Optional;

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
import com.reto.Banco.entity.TransactionEntity;
import com.reto.Banco.service.ProductSevice;
import com.reto.Banco.service.TransactionService;




@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/transaction")
public class transactionController {

    @Autowired 
    ProductSevice productService;

    @Autowired 
    TransactionService transactionService;

    //create transaction

    @PostMapping("/{cuentaid}/consiginar")
    public ResponseEntity<GeneralResponse<Integer>>  consiginar(  @RequestBody TransactionEntity transaction ,
     @PathVariable("cuentaid") Long cuentaId) {
        GeneralResponse<Integer> respuesta = new GeneralResponse<>();
		Integer datos = null;
		Optional<ProductEntity> producto = null;
		String mensaje = null;			
		HttpStatus estadoHttp = null;

        try{

            producto = productService.findById(cuentaId);
			datos = 0;	

            if (transaction.getValor()<=0) {
				mensaje = "1 - Value should be greater than $US 0.00 ";		
				respuesta.setDatos(datos);
				respuesta.setMensaje(mensaje);
				respuesta.setPeticionExitosa(false);
				estadoHttp = HttpStatus.NOT_FOUND;				
				return new ResponseEntity<>(respuesta, estadoHttp);
			}

			if (!producto.get().getEstado().equals("cancelled")) {
				transaction.setSaldoInicial(producto.get().getSaldo());
				producto.get().setSaldo(producto.get().getSaldo() + transaction.getValor());//consignación
				transaction.setSaldoFinal(producto.get().getSaldo());
				transaction.setTypeTransaction("deposit");
				// transaction.setTipoDebito("credit");
				transaction.setDateCreate(LocalDate.now());
                //cuenta id producto
				transaction.setCuentaId(cuentaId);
				transaction.setCuentaDestino((long) 0);
				productService.CreateProduct(producto.get());
				transactionService.CreateTransaction(transaction);
				
				
				mensaje = "0 - Deposit created successfully";		
				respuesta.setDatos(datos);
				respuesta.setMensaje(mensaje);
				respuesta.setPeticionExitosa(true);
				estadoHttp = HttpStatus.CREATED;
			}else {				
				mensaje = "1 - Deposit not made, account N°= " +producto.get().getNumeroCuenta() +" is canceled";		
				respuesta.setDatos(datos);
				respuesta.setMensaje(mensaje);
				respuesta.setPeticionExitosa(true);		
				estadoHttp = HttpStatus.OK;
			}

        }catch(Exception e)
        {
            estadoHttp = HttpStatus.INTERNAL_SERVER_ERROR;
			mensaje = "There was an error. Contact the administrator";
			respuesta.setMensaje(mensaje);
			respuesta.setPeticionExitosa(false);

        }

        return new ResponseEntity<>(respuesta, estadoHttp);
    }

//=====================================================================  whitdraw    ==================================================================================
    

    @PostMapping("/{idProducto}/retirar")
	public ResponseEntity<GeneralResponse<Integer>> retirarSaldo(@RequestBody TransactionEntity movimientoOrigen,
			@PathVariable("idProducto") long idCuenta) {
		
		GeneralResponse<Integer> mensajeRespuestaOrigen = new GeneralResponse<>();
		Integer datos = null;
		Optional<ProductEntity> productoOrigen = null;
		String mensaje = null;
		TransactionEntity movimientoGMF = null;
		HttpStatus estadoHttp = null;
		
		try {
			productoOrigen = productService.findById(idCuenta);
            //validate value to 
			if (movimientoOrigen.getValor()<=0) {
				mensaje = "1 - Value should be greater than $US 0.00 ";		
				mensajeRespuestaOrigen.setDatos(datos);
				mensajeRespuestaOrigen.setMensaje(mensaje);
				mensajeRespuestaOrigen.setPeticionExitosa(false);
				estadoHttp = HttpStatus.NOT_FOUND;				
				return new ResponseEntity<>(mensajeRespuestaOrigen, estadoHttp);
			}
			double valorTransaccion = movimientoOrigen.getValor();		
			
			boolean saldoValidado = validarSaldo(productoOrigen, valorTransaccion);
			
			mensajeRespuestaOrigen = valEstadosProductoOri(productoOrigen, saldoValidado); //valida estado producto/cuenta de origen
			
			if (saldoValidado && mensajeRespuestaOrigen.isPeticionExitosa()){
				movimientoOrigen.setTypeTransaction(TransactionTypeEnum.withdraw.toString());
				movimientoOrigen.setCuentaDestino(0);
				
				movimientoOrigen = realizarMovimientoDebito(productoOrigen, movimientoOrigen);
				productoOrigen.get().setSaldo(movimientoOrigen.getSaldoFinal());				
				productService.CreateProduct(productoOrigen.get());
				transactionService.CreateTransaction(movimientoOrigen);
				
				movimientoGMF = realizarMovimientoGMF(productoOrigen, valorTransaccion);
				productoOrigen.get().setSaldo(movimientoGMF.getSaldoFinal());				
				productService.CreateProduct(productoOrigen.get());	
				transactionService.CreateTransaction(movimientoGMF);
				
				mensaje = mensajeRespuestaOrigen.getMensaje() + " (Withdrawal)";
				estadoHttp = HttpStatus.CREATED;				
			}else if(!saldoValidado) {
				mensaje = mensajeRespuestaOrigen.getMensaje() + " (Withdrawal)";
				estadoHttp = HttpStatus.OK;				
			}
			else if(!mensajeRespuestaOrigen.isPeticionExitosa()) {
				mensaje = mensajeRespuestaOrigen.getMensaje() + " (Withdrawal)";
				estadoHttp = HttpStatus.OK;				
			}else {
				mensaje = "1 - Unidentified method error, contact support" + " (Withdrawal)";
				estadoHttp = HttpStatus.OK;					
			}
			datos = 0;
			mensajeRespuestaOrigen.setDatos(datos);
			mensajeRespuestaOrigen.setMensaje(mensaje);
			mensajeRespuestaOrigen.setPeticionExitosa(true);		
			
		} catch (Exception e) {
			estadoHttp = HttpStatus.INTERNAL_SERVER_ERROR;
			mensaje = "Hubo un fallo. Contacte al administrador";
			mensajeRespuestaOrigen.setMensaje(mensaje);
			mensajeRespuestaOrigen.setPeticionExitosa(false);
		}
		return new ResponseEntity<>(mensajeRespuestaOrigen, estadoHttp);		
	}



    private boolean validarSaldo(Optional<ProductEntity> productoOrigen, double valorTransaccion) {
		boolean saldoValidado = false;
		
		double saldoGMF = productoOrigen.get().getSaldo() - valorTransaccion
				- valorTransaccion * 0.004;

		if (productoOrigen.get().getTipoCuenta().toLowerCase().equals("ahorro") && productoOrigen.get().getEstado()
				.toLowerCase().equals("enabled")
				&& saldoGMF >= 0) {
			saldoValidado = true;
		}
		if (productoOrigen.get().getTipoCuenta().toLowerCase().equals("corriente")
				&& productoOrigen.get().getEstado().toLowerCase().equals("enabled") && saldoGMF >= -5000) {
			saldoValidado = true;
		}	
		return saldoValidado;
	}


    private GeneralResponse<Integer> valEstadosProductoOri(Optional<ProductEntity> productoOrigen, boolean saldoValidado) {
		
		GeneralResponse<Integer> mensajeRespuesta = new GeneralResponse<>();
		
		if (productoOrigen.get().getEstado().toLowerCase().equals("desabled")) {
			mensajeRespuesta.setMensaje("1 - The account is desabled");
			mensajeRespuesta.setPeticionExitosa(false);
			return mensajeRespuesta;			
		} else if (productoOrigen.get().getEstado().toLowerCase().equals("canceled")) {
			mensajeRespuesta.setMensaje("1- the account is canceled");
			mensajeRespuesta.setPeticionExitosa(false);
			return mensajeRespuesta;
		} else if (productoOrigen.get().getTipoCuenta().toLowerCase().equals("AHORRO") && !saldoValidado) {
			mensajeRespuesta.setMensaje("2 - Not enought funds, the balance with the GMF must be greater than or equal to US$0.0");
			mensajeRespuesta.setPeticionExitosa(false);
			return mensajeRespuesta;
		} else if (productoOrigen.get().getTipoCuenta().toLowerCase().equals("CORRIENTE") && !saldoValidado) {
			mensajeRespuesta.setMensaje("2 - Not enought funds - Current account cannot be overdrawn by more than US$5,000");
			mensajeRespuesta.setPeticionExitosa(false);
			return mensajeRespuesta;
		} else {
			mensajeRespuesta.setMensaje("0 - Operation successfully");
			mensajeRespuesta.setPeticionExitosa(true);		
			return mensajeRespuesta;
			}		
	}


    private TransactionEntity realizarMovimientoDebito(Optional<ProductEntity> productoOrigen,
    TransactionEntity movimientoOrigen) {		
            movimientoOrigen.setSaldoInicial(productoOrigen.get().getSaldo());
            double saldo = productoOrigen.get().getSaldo() - movimientoOrigen.getValor();	
            movimientoOrigen.setSaldoFinal(saldo);
            movimientoOrigen.setDateCreate(LocalDate.now());
            movimientoOrigen.setTypeDébito("debit");
            movimientoOrigen.setCuentaId(productoOrigen.get().getId());
            return movimientoOrigen;
}



private TransactionEntity realizarMovimientoGMF(Optional<ProductEntity> productoOrigen, double valorTransaccion) {
    TransactionEntity movimientoGMF = new TransactionEntity();
    movimientoGMF.setSaldoInicial(productoOrigen.get().getSaldo());
    
    double saldoGMF = productoOrigen.get().getSaldo() - valorTransaccion * 0.004;		
    movimientoGMF.setSaldoFinal(saldoGMF);
    movimientoGMF.setValor(valorTransaccion * 0.004);
    movimientoGMF.setDateCreate(LocalDate.now());
    // movimientoGMF.setTipoMovimiento("GMF");
    movimientoGMF.setTypeDébito("debit");
    movimientoGMF.setDescripcion("GMF");
    movimientoGMF.setCuentaId(productoOrigen.get().getId());
    movimientoGMF.setCuentaDestino(0);
    return movimientoGMF;
}


//----------------------------------------------------------------  transferencias entre cuentas    ------------------------------------------------------------

//metodos 

//objetivo primero hacer una trasnferencia entre cuentas 
/* Requier id actual product
 * Requier id of the count or count number product   for destiny count
 * 
 */



@PostMapping("/trasnfer/{idProducto}")
public ResponseEntity<GeneralResponse<Integer>>    TransferMov(@RequestBody TransactionEntity movimientoOrigen,
		@PathVariable("idProducto") long idCuenta) {

			GeneralResponse<Integer> mensajeRespuestaOrigen = new GeneralResponse<>();
			Integer datos = null;
			Optional<ProductEntity> productoOrigen = null;
			Optional<ProductEntity> productoDestiny = null;
			String mensaje = null;
			// TransactionEntity movimientoGMF = null;
			HttpStatus estadoHttp = null;
			TransactionEntity movimientoDestiny  = null;

			try{
				productoOrigen =  productService.findById(idCuenta);
				productoDestiny = productService.findById(movimientoOrigen.getCuentaDestino());

				if (movimientoOrigen.getValor()<=0) {
					mensaje = "1 - Value should be greater than $US 0.00 ";		
					mensajeRespuestaOrigen.setDatos(datos);
					mensajeRespuestaOrigen.setMensaje(mensaje);
					mensajeRespuestaOrigen.setPeticionExitosa(false);
					estadoHttp = HttpStatus.NOT_FOUND;				
					return new ResponseEntity<>(mensajeRespuestaOrigen, estadoHttp);
				}

				//verificador numero cuenta.  <<- mas adelante  validaddores
				
				
				double saldoInicialOrigen = productoOrigen.get().getSaldo() ;
				double saldoInicialDestino = productoDestiny.get().getSaldo() ;
				//verificador de que el saldo disponible cumpla con el monton para hacer la transferencia
				// saldo disponible -  valor
				// saldo disponible +  valor  

				productoOrigen.get().setSaldoDisponible(productoOrigen.get().getSaldoDisponible() - movimientoOrigen.getValor());
				productoDestiny.get().setSaldoDisponible(productoOrigen.get().getSaldoDisponible() + movimientoOrigen.getValor());
				// si el saldo disponible acepta la validadcion 
				// se hacer el seteo en el saldo total 



				//setter los datos de transferencia 
				movimientoOrigen.setTypeTransaction(TransactionTypeEnum.transfer.toString());
				movimientoOrigen.setSaldoInicial(saldoInicialOrigen);


				// movimientoOrigen.setCuentaDestino(0);
				movimientoOrigen = new TransactionEntity(TransactionTypeEnum.transfer.toString(),
														LocalDate.now(),
														productoOrigen.get().getId() ,
														"GMF",
													    "Debit",
														productoOrigen.get().getSaldo() ,
														productoOrigen.get().getSaldoDisponible(),
														saldoInicialOrigen,
													    movimientoOrigen.getValor(),
														productoOrigen.get().getSaldo(),
														movimientoOrigen.getCuentaDestino()														  
				 );

				// movimientoOrigen.setCuentaDestino(0);
				movimientoDestiny = new TransactionEntity(TransactionTypeEnum.transfer.toString(),
														LocalDate.now(),
														productoDestiny.get().getId() ,
														"GMF",
													    "Credit",
														productoDestiny.get().getSaldo() ,
														productoDestiny.get().getSaldoDisponible(),
														saldoInicialDestino,
													    movimientoOrigen.getValor(),
														productoDestiny.get().getSaldo(),0														  
				 );


				 productService.CreateProduct(productoOrigen.get());	
				 productService.CreateProduct(productoDestiny.get());

				transactionService.CreateTransaction(movimientoOrigen);
				transactionService.CreateTransaction(movimientoDestiny);


				mensaje ="test";
				estadoHttp = HttpStatus.OK;		
				datos = 0;
				mensajeRespuestaOrigen.setDatos(datos);
				mensajeRespuestaOrigen.setMensaje(mensaje);
				mensajeRespuestaOrigen.setPeticionExitosa(true);	



			}catch(Exception e){

				estadoHttp = HttpStatus.INTERNAL_SERVER_ERROR;
				mensaje = "Hubo un fallo. Contacte al administrador";
				mensajeRespuestaOrigen.setMensaje(mensaje);
				mensajeRespuestaOrigen.setPeticionExitosa(false);
			}

   				// System.out.println("hola mundo");
			return new ResponseEntity<>(mensajeRespuestaOrigen, estadoHttp);
		}


  public enum TransactionTypeEnum {
        dispous, 
        withdraw,
		transfer
    }
    
}

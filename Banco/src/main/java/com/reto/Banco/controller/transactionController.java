package com.reto.Banco.controller;


import java.io.Console;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
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

import ch.qos.logback.core.joran.conditional.ElseAction;




@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/transaction")
public class transactionController {

    @Autowired 
    ProductSevice productService;

    @Autowired 
    TransactionService transactionService;

	private static final DecimalFormat df = new DecimalFormat("0.00");

    //create transaction

    @PostMapping("/disposet/{cuentaid}")
    public ResponseEntity<GeneralResponse<Integer>>  consiginar(  @RequestBody TransactionEntity transaction ,
     @PathVariable("cuentaid") Long cuentaId) {
        GeneralResponse<Integer> respuesta = new GeneralResponse<>();
		Integer datos = null;
		Optional<ProductEntity> producto = null;
		String mensaje = null;			
		HttpStatus estadoHttp = null;
		double GMFBalanceAvaiable = 0;
		double GMFValueMov = 0;
		double RealMovValue = 0;

		TransactionEntity movGMF  = null;



        try{

            producto = productService.findById(cuentaId);
			datos = 0;	

            if (transaction.getValue()<=0) {
				mensaje = "1 - Value should be greater than $US 0.00 ";		
				respuesta.setDatos(datos);
				respuesta.setMensaje(mensaje);
				respuesta.setPeticionExitosa(false);
				estadoHttp = HttpStatus.NOT_FOUND;				
				return new ResponseEntity<>(respuesta, estadoHttp);
			}

			GMFBalanceAvaiable = 	CalCGMF(producto.get().getBalance(), producto.get())  ;
			GMFValueMov = CalCGMF(transaction.getValue(), producto.get());
			RealMovValue =  transaction.getValue()- GMFValueMov;


			if (!producto.get().getState().equalsIgnoreCase("cancelled")) {
				//real

				transaction.setInitialBalance(producto.get().getBalance() );
				
				//eliminate decimals output :  0.00
				BigDecimal bd = new BigDecimal(producto.get().getBalance()-  GMFBalanceAvaiable +  RealMovValue).setScale(2, RoundingMode.HALF_UP);

				transaction.setAvaiableBalance( bd.doubleValue());
				producto.get().setBalanceAvailable( bd.doubleValue());


				bd = new BigDecimal(producto.get().getBalance() + RealMovValue).setScale(2, RoundingMode.HALF_UP);

				transaction.setBalance(bd.doubleValue());
				producto.get().setBalance(bd.doubleValue());//consignación 

				transaction.setFinalBalance(producto.get().getBalance());
				
				transaction.setTypeTransaction("deposit");
				
				
				// transaction.setTipoDebito("credit");
				transaction.setDateCreate(LocalDate.now());
                //cuenta id producto
				transaction.setAccountId(cuentaId);
				transaction.setDestinyAccount((long) 0);

				productService.CreateProduct(producto.get());
								
				transaction.setTypeDebito("Credit");


				// if(producto.get().getExcludeGMF() == false)
				// {
					movGMF = new TransactionEntity(TransactionTypeEnum.transfer.toString(),
					LocalDate.now(),
					producto.get().getId(),
					"GMF",
					"Credit",
					GMFValueMov,
					GMFValueMov,
					GMFValueMov,
					transaction.getValue(),
					producto.get().getBalance(),
					0														  
					);
					transactionService.CreateTransaction(movGMF);
				// }

				transactionService.CreateTransaction(transaction);


				
				
				mensaje = "0 - Deposit created successfully";		
				respuesta.setDatos(datos);
				respuesta.setMensaje(mensaje);
				respuesta.setPeticionExitosa(true);
				estadoHttp = HttpStatus.CREATED;
			}else {				
				mensaje = "1 - Deposit not made, account N°= " +producto.get().getNumAccont() +" is canceled";		
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

	public double CalCGMF( double Balance , ProductEntity product)
	{   
		double Result;
		if(product.getExcludeGMF() == false)
		{
			Result= Balance * 0.004;

			return Result;
		}
		else{
			Result= 0;

			return Result;
		}
		
		
	}

//=====================================================================  whitdraw    ==================================================================================
    

    @PostMapping("/withdraw/{idProducto}")
	public ResponseEntity<GeneralResponse<Integer>> retirarSaldo(@RequestBody TransactionEntity transaction,
			@PathVariable("idProducto") long idCuenta) {
		
		
		GeneralResponse<Integer> respuesta = new GeneralResponse<>();
		Integer datos = null;
		Optional<ProductEntity> producto = null;
		String mensaje = null;			
		HttpStatus estadoHttp = null;
		double GMFBalanceAvaiable = 0;
		double GMFValueMov = 0;
		double RealMovValue = 0;
		
		TransactionEntity movGMF  = null;

        try{

            producto = productService.findById(idCuenta);
			datos = 0;	


			 

            if (transaction.getValue()<=0) {
				mensaje = "1 - Value should be greater than $US 0.00 ";		
				respuesta.setDatos(datos);
				respuesta.setMensaje(mensaje);
				respuesta.setPeticionExitosa(false);
				estadoHttp = HttpStatus.CONFLICT;				
				return new ResponseEntity<>(respuesta, estadoHttp);
			} 
			
			if(Tipocuenta.Savings.toString().equalsIgnoreCase(producto.get().getTypeAccount()) &&
				producto.get().getBalanceAvailable() - transaction.getValue() < 0 
			){
				mensaje = "1 - Savings mov shouldn´t be less than $US 0.00 in the balance.";		
				respuesta.setDatos(datos);
				respuesta.setMensaje(mensaje);
				respuesta.setPeticionExitosa(false);
				estadoHttp = HttpStatus.CONFLICT;				
				return new ResponseEntity<>(respuesta, estadoHttp);
			
			} else if(producto.get().getBalanceAvailable() - transaction.getValue() < -3000000 ){
				mensaje = "1 - Checking  mov shouldn´t be less than $US 3.000.000.00 in the balance.";		
				respuesta.setDatos(datos);
				respuesta.setMensaje(mensaje);
				respuesta.setPeticionExitosa(false);
				estadoHttp = HttpStatus.CONFLICT;				
				return new ResponseEntity<>(respuesta, estadoHttp);

			}

			if (State.disenabled.toString().equalsIgnoreCase( producto.get().getState().toString())   ) {
				mensaje = "1 - Disenabled accounts mustn´t allow a debit mov type.";		
				respuesta.setDatos(datos);
				respuesta.setMensaje(mensaje);
				respuesta.setPeticionExitosa(false);
				estadoHttp = HttpStatus.CONFLICT;				
				return new ResponseEntity<>(respuesta, estadoHttp);
			}
			
			//GMF Values
			GMFBalanceAvaiable = 	CalCGMF(producto.get().getBalance(), producto.get())  ;
			GMFValueMov = CalCGMF(transaction.getValue(), producto.get());
			RealMovValue =  transaction.getValue()- GMFValueMov;


			if (!producto.get().getState().equalsIgnoreCase("cancelled")) {
				transaction.setInitialBalance(producto.get().getBalance() );


				transaction.setAvaiableBalance( (producto.get().getBalance() - GMFBalanceAvaiable) -  RealMovValue);
				producto.get().setBalanceAvailable((producto.get().getBalance()- GMFBalanceAvaiable) -   RealMovValue);
				
				transaction.setBalance(producto.get().getBalance()- RealMovValue);
				producto.get().setBalance(producto.get().getBalance() - RealMovValue);//consignación
				
				transaction.setFinalBalance(producto.get().getBalance());
				transaction.setTypeTransaction("whitdraw");
				// transaction.setTipoDebito("credit");
				transaction.setDateCreate(LocalDate.now());
                //cuenta id producto
				transaction.setAccountId(idCuenta);
				transaction.setDestinyAccount((long) 0);

				transaction.setTypeDebito(DefineTypeMov(producto.get().getTypeAccount()));

				// if(producto.get().getExcludeGMF() == false)
				// {
					movGMF = new TransactionEntity(TransactionTypeEnum.transfer.toString(),
					LocalDate.now(),
					producto.get().getId() ,
					"GMF",
					"Credit",
					GMFValueMov,
					GMFValueMov,
					GMFValueMov,
					transaction.getValue(),
					producto.get().getBalance(),
					0														  
					);
					transactionService.CreateTransaction(transaction);

				// }

				

				productService.CreateProduct(producto.get());

				transactionService.CreateTransaction(movGMF);
				
				
				mensaje = "0 - Deposit created successfully";		
				respuesta.setDatos(datos);
				respuesta.setMensaje(mensaje);
				respuesta.setPeticionExitosa(true);
				estadoHttp = HttpStatus.CREATED;
			}else {				
				mensaje = "1 - Deposit not made, account N°= " +producto.get().getNumAccont() +" is canceled";		
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
	



    private boolean validarSaldo(Optional<ProductEntity> productoOrigen, double valorTransaccion) {
		boolean saldoValidado = false;
		
		double saldoGMF = productoOrigen.get().getBalance() - valorTransaccion
				- valorTransaccion * 0.004;

		if (productoOrigen.get().getTypeAccount().toLowerCase().equals("ahorro") && productoOrigen.get().getState()
				.toLowerCase().equals("enabled")
				&& saldoGMF >= 0) {
			saldoValidado = true;
		}
		if (productoOrigen.get().getTypeAccount().toLowerCase().equals("corriente")
				&& productoOrigen.get().getState().toLowerCase().equals("enabled") && saldoGMF >= -5000) {
			saldoValidado = true;
		}	
		return saldoValidado;
	}


    private GeneralResponse<Integer> valEstadosProductoOri(Optional<ProductEntity> productoOrigen, boolean saldoValidado) {
		
		GeneralResponse<Integer> mensajeRespuesta = new GeneralResponse<>();
		
		if (productoOrigen.get().getState().toLowerCase().equals("desabled")) {
			mensajeRespuesta.setMensaje("1 - The account is desabled");
			mensajeRespuesta.setPeticionExitosa(false);
			return mensajeRespuesta;			
		} else if (productoOrigen.get().getState().toLowerCase().equals("canceled")) {
			mensajeRespuesta.setMensaje("1- the account is canceled");
			mensajeRespuesta.setPeticionExitosa(false);
			return mensajeRespuesta;
		} else if (productoOrigen.get().getTypeAccount().toLowerCase().equals("AHORRO") && !saldoValidado) {
			mensajeRespuesta.setMensaje("2 - Not enought funds, the balance with the GMF must be greater than or equal to US$0.0");
			mensajeRespuesta.setPeticionExitosa(false);
			return mensajeRespuesta;
		} else if (productoOrigen.get().getTypeAccount().toLowerCase().equals("CORRIENTE") && !saldoValidado) {
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
            movimientoOrigen.setInitialBalance(productoOrigen.get().getBalance());
            double saldo = productoOrigen.get().getBalance() - movimientoOrigen.getValue();	
            movimientoOrigen.setFinalBalance(saldo);
            movimientoOrigen.setDateCreate(LocalDate.now());
            movimientoOrigen.setTypeDebito("debit");
            movimientoOrigen.setAccountId(productoOrigen.get().getId());
            return movimientoOrigen;
}



private TransactionEntity realizarMovimientoGMF(Optional<ProductEntity> productoOrigen, double valorTransaccion) {
    TransactionEntity movimientoGMF = new TransactionEntity();
    movimientoGMF.setInitialBalance(productoOrigen.get().getBalance());
    
    double saldoGMF = productoOrigen.get().getBalance() - valorTransaccion * 0.004;		
    movimientoGMF.setFinalBalance(saldoGMF);
    movimientoGMF.setValue(valorTransaccion * 0.004);
    movimientoGMF.setDateCreate(LocalDate.now());
    // movimientoGMF.setTipoMovimiento("GMF");
    movimientoGMF.setTypeDebito("debit");
    movimientoGMF.setDescription("GMF");
    movimientoGMF.setAccountId(productoOrigen.get().getId());
    movimientoGMF.setDestinyAccount(0);
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
public ResponseEntity<GeneralResponse<Integer>>    TransferMov(@RequestBody TransactionEntity movOrigin,
		@PathVariable("idProducto") long idCuenta) {

			GeneralResponse<Integer> mensajeRespuestaOrigen = new GeneralResponse<>();
			Integer datos = null;
			Optional<ProductEntity> productoOrigen = null;
			Optional<ProductEntity> productoDestiny = null;
			String mensaje = null;
			// TransactionEntity movimientoGMF = null;
			HttpStatus estadoHttp = null;
			TransactionEntity movimientoDestiny  = null;

			TransactionEntity movGMF  = null;

			double GMFBalanceAvaiableOrigin = 0;
			double GMFBalanceAvaiableDestiny = 0;
			double GMFValueMov = 0;
			double RealMovValue = 0;

			try{
				productoOrigen =  productService.findById(idCuenta);
				productoDestiny = productService.findById(movOrigin.getDestinyAccount());
				
				movOrigin.setDateCreate(LocalDate.now());

				if (movOrigin.getValue()<=0) {
					mensaje = "1 - Value should be greater than $US 0.00 ";		
					mensajeRespuestaOrigen.setDatos(datos);
					mensajeRespuestaOrigen.setMensaje(mensaje);
					mensajeRespuestaOrigen.setPeticionExitosa(false);
					estadoHttp = HttpStatus.NOT_FOUND;				
					return new ResponseEntity<>(mensajeRespuestaOrigen, estadoHttp);
				}

			if(Tipocuenta.Savings.toString().equalsIgnoreCase(productoOrigen.get().getTypeAccount()) &&
						productoOrigen.get().getBalanceAvailable() - movOrigin.getValue() < 0 
			){
				mensaje = "1 - Saving Mov shouldn´t be less than $US 0.00 in the balance.";		
				mensajeRespuestaOrigen.setDatos(datos);
				mensajeRespuestaOrigen.setMensaje(mensaje);
				mensajeRespuestaOrigen.setPeticionExitosa(false);
				estadoHttp = HttpStatus.CONFLICT;				
				return new ResponseEntity<>(mensajeRespuestaOrigen, estadoHttp);
			} 
			else if(productoOrigen.get().getBalanceAvailable() - movOrigin.getValue() < -3000000 ){
				mensaje = "1 - Checking  mov shouldn´t be less than $US 3.000.000.00 in the balance.";		
				mensajeRespuestaOrigen.setDatos(datos);
				mensajeRespuestaOrigen.setMensaje(mensaje);
				mensajeRespuestaOrigen.setPeticionExitosa(false);
				estadoHttp = HttpStatus.CONFLICT;				
				return new ResponseEntity<>(mensajeRespuestaOrigen, estadoHttp);

			}


			if (State.disenabled.toString().equalsIgnoreCase( productoOrigen.get().getState().toString())   ) {
				mensaje = "1 - Disenabled accounts mustn´t allow a debit mov type.";		
				mensajeRespuestaOrigen.setDatos(datos);
				mensajeRespuestaOrigen.setMensaje(mensaje);
				mensajeRespuestaOrigen.setPeticionExitosa(false);
				estadoHttp = HttpStatus.CONFLICT;				
				return new ResponseEntity<>(mensajeRespuestaOrigen, estadoHttp);
			}



				//GMF Values
				GMFBalanceAvaiableOrigin = 	CalCGMF(productoOrigen.get().getBalance(), productoOrigen.get()) ;
				GMFBalanceAvaiableDestiny = 	CalCGMF(productoDestiny.get().getBalance(), productoDestiny.get() ) ;
				GMFValueMov = CalCGMF(movOrigin.getValue() , productoOrigen.get() );
				RealMovValue =  movOrigin.getValue()- GMFValueMov;

				//verificador numero cuenta.  <<- mas adelante  validaddores
				
				if (!productoOrigen.get().getState().equals("cancelled")){
					
					double saldoInicialOrigen = productoOrigen.get().getBalance() ;
					double saldoInicialDestino = productoDestiny.get().getBalance() ;
					//verificador de que el saldo disponible cumpla con el monton para hacer la transferencia
					// saldo disponible -  valor
					// saldo disponible +  valor
				
					double movBalanceeAvailableOrigen = (productoOrigen.get().getBalance() - GMFBalanceAvaiableOrigin ) - RealMovValue;
					double movBalanceAvailableDestiny = (productoDestiny.get().getBalance() - GMFBalanceAvaiableDestiny) + RealMovValue;
					
					double movBalanceOrigen = productoOrigen.get().getBalance() - RealMovValue;
					double movBalanceDestiny = productoDestiny.get().getBalance() + RealMovValue;
					


				productoOrigen.get().setBalanceAvailable(movBalanceeAvailableOrigen);
				productoDestiny.get().setBalanceAvailable(movBalanceAvailableDestiny);
				
				productoOrigen.get().setBalance(movBalanceOrigen);
				productoDestiny.get().setBalance(movBalanceDestiny);
				
				// si el saldo disponible acepta la validadcion 
				// se hacer el seteo en el saldo total 



				//setter los datos de transferencia 
				movOrigin.setTypeTransaction(TransactionTypeEnum.transfer.toString());
				movOrigin.setInitialBalance(saldoInicialOrigen);


				// DefineTypeMov(productoOrigen.get().getTypeAccount())
				movOrigin = new TransactionEntity(TransactionTypeEnum.transfer.toString(),
														LocalDate.now(),
														productoOrigen.get().getId() ,
														movOrigin.getDescription(),
													    "Debit",
														movBalanceOrigen,
														movBalanceeAvailableOrigen,
														saldoInicialOrigen,
													    movOrigin.getValue(),
														productoOrigen.get().getBalance(),
														movOrigin.getDestinyAccount()														  
				 );

				// movimientoOrigen.setCuentaDestino(0);
				movimientoDestiny = new TransactionEntity(TransactionTypeEnum.transfer.toString(),
														LocalDate.now(),
														productoDestiny.get().getId() ,
														movOrigin.getDescription(),
													    "Credit",
														movBalanceDestiny,
														movBalanceAvailableDestiny,
														saldoInicialDestino,
													    movOrigin.getValue(),
														productoDestiny.get().getBalance(),
														0														  
														 );


														//  if( productoOrigen.get().getExcludeGMF() == false)
														//  {

															 movGMF = new TransactionEntity(TransactionTypeEnum.transfer.toString(),
																						 LocalDate.now(),
																						 productoOrigen.get().getId() ,
																						 "GMF",
																						 "Credit",
																						 GMFValueMov,
																						 GMFValueMov,
																						 GMFValueMov,
																						 movOrigin.getValue(),
																						 productoOrigen.get().getBalance(),
																						 0														  
																						 );
																						 transactionService.CreateTransaction(movGMF);

														//  }

														 
														 productService.CreateProduct(productoOrigen.get());	
														 productService.CreateProduct(productoDestiny.get());
														 
														 
														 transactionService.CreateTransaction(movOrigin);
														 transactionService.CreateTransaction(movimientoDestiny);
														 
														 
														 mensaje ="0 - Transfer was succesful";
														 estadoHttp = HttpStatus.OK;		
														 datos = 0;
														 mensajeRespuestaOrigen.setDatos(datos);
														 mensajeRespuestaOrigen.setMensaje(mensaje);
														 mensajeRespuestaOrigen.setPeticionExitosa(true);	
														 
														}else
														{
															mensaje ="1 - Transfer wasn´t State Canceled";
															estadoHttp = HttpStatus.CONFLICT;		
															datos = 0;
															mensajeRespuestaOrigen.setDatos(datos);
															mensajeRespuestaOrigen.setMensaje(mensaje);
															mensajeRespuestaOrigen.setPeticionExitosa(false);	
														}
														 
														 
														}catch(Exception e){
															
															estadoHttp = HttpStatus.INTERNAL_SERVER_ERROR;
															mensaje = "Hubo un fallo. Contacte al administrador";
															mensajeRespuestaOrigen.setMensaje(mensaje);
															mensajeRespuestaOrigen.setPeticionExitosa(false);
			}

   				// System.out.println("hola mundo");
			return new ResponseEntity<>(mensajeRespuestaOrigen, estadoHttp);
		}


		//================================// get

		@GetMapping("/get/{idProducto}")
public ResponseEntity<GeneralResponse<List<TransactionEntity>>> getAllMovByIdProducEntity(@PathVariable("idProducto") long idCuenta) {
			GeneralResponse<List<TransactionEntity>> mensajeRespuestaOrigen = new GeneralResponse<>();
			List<TransactionEntity> datos = null;
			String mensaje = null;
			// TransactionEntity movimientoGMF = null;
			HttpStatus estadoHttp = null;
			TransactionEntity movimientoDestiny  = null;

			//  get all mov id_cuenta
			try{
				datos =	transactionService.findByclienteId(idCuenta);

				mensajeRespuestaOrigen.setDatos(datos);
				
				mensaje = "0 - Deposit created successfully";		
				estadoHttp = HttpStatus.OK;		
				mensajeRespuestaOrigen.setDatos(datos);
				
				mensajeRespuestaOrigen.setMensaje(mensaje);
				mensajeRespuestaOrigen.setPeticionExitosa(true);	

			}
			catch( Exception e)
			{
				estadoHttp = HttpStatus.INTERNAL_SERVER_ERROR;
				mensaje = "Hubo un fallo. Contacte al administrador";
				mensajeRespuestaOrigen.setMensaje(mensaje);
				mensajeRespuestaOrigen.setPeticionExitosa(false);
			}
			
			System.out.println(idCuenta);

			// return ;
			return new ResponseEntity<>(mensajeRespuestaOrigen, estadoHttp);

		}

		public String DefineTypeMov(String TypeAccountName){

			String value ="";
			if( Tipocuenta.Savings.toString().equalsIgnoreCase(TypeAccountName))
			 value = "Debit";			
			else 
			value = "Credit";

			return value;
		}

	public enum Tipocuenta
		{
			Savings,
			checking
		}


  public enum TransactionTypeEnum {
        dispous, 
        withdraw,
		transfer
    }

	public enum State
    {
        enabled,
        disenabled,
        cancelled
    }
    
}

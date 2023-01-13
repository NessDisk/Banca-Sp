import { transition } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Movement } from '../../models/moviment';
import { MovementServices } from '../../services/movement.service';
import { Location } from '@angular/common';

//extension
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-mov-transfer',
  templateUrl: './mov-transfer.component.html',
  styleUrls: ['./mov-transfer.component.css']
})
export class MovTransferComponent implements OnInit {

  wordKey: string ="test";
  productId: number;

  typeTransaction:string ="";
  Descripcion:string;
  valor:number;
  cuentaDestino:number;

  movement:Movement[] =[];


  constructor(private movService: MovementServices,
              private route: ActivatedRoute,
              private _location: Location,
              private toastr: ToastrService,
               private _router: Router
              ){ }


  ngOnInit(): void {
    this.productId = this.route.snapshot.params['id'];
  }


  goBack(){
    setTimeout(() => {
      this._location.back();
      
    }, 1000);
  }
//add 
  AddDisposet():void{

    const movement = new Movement(this.typeTransaction,
                                  this.cuentaDestino,
                                  this.Descripcion,
                                  this.valor,
                                  this.cuentaDestino);

    if(this.typeTransaction == "Deposit")
    {
      this.movService.DespositByProductId( this.productId,movement ).subscribe(
        (response) =>{
          if(response.peticionExitosa){
            this.movement = response.datos;
            if(response.mensaje == "0 - Deposit created successfully")
            {
              this.toastr.success(response.mensaje);
            }
          }
        },err =>{
          console.log(err)
        }
      );
      
    }
    else if(this.typeTransaction == "Withdraw")
    {
      this.movService.WithdByProductId( this.productId,movement ).subscribe(
        (response) =>{
          if(response.peticionExitosa){      
            
            this.movement = response.datos;
           
            console.log(response.mensaje)
            // if( response.mensaje == "1 - Savings mov shouldn´t be less than $US 0.00 in the balance."
            //   || response.mensaje ==  "1 - Checking  mov shouldn´t be less than $US 3.000.000.00 in the balance."
            //   || response.mensaje == "1 - Disenabled accounts mustn´t allow a debit mov type."
            //   )
            // {              
            //   this.toastr.info(response.mensaje);
            // }
             if(response.mensaje == "0 - Deposit created successfully"){
              this.toastr.success(response.mensaje);
            }else {

              this.toastr.info(response.mensaje);
            }

          }
        },err =>{
          console.log(err)
        }
      );
    }
    else if(this.typeTransaction == "Transfer"){
      this.movService.Transfer( this.productId,movement ).subscribe(
        (response) =>{
          if(response.peticionExitosa){
            this.movement = response.datos;
            if( response.mensaje == "1 - Saving Mov shouldn´t be less than $US 0.00 in the balance."
             || response.mensaje ==  "1 - Checking  mov shouldn´t be less than $US 3.000.000.00 in the balance."
            )
            {              
              this.toastr.info(response.mensaje);
            }
            else if(response.mensaje == "0 - Transfer was succesful"){
              this.toastr.success(response.mensaje);
            }
          }
        },err =>{
                     
          
          console.log(err)
          this.toastr.info(err.error.mensaje);
        }
      );
    }

  }



}

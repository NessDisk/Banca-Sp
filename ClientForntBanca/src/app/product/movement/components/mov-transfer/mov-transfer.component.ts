import { transition } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Movement } from '../../models/moviment';
import { MovementServices } from '../../services/movement.service';

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


  constructor(private movService: MovementServices, private route: ActivatedRoute){ }


  ngOnInit(): void {
    this.productId = this.route.snapshot.params['id'];
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
            console.log(this.movement)
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
            console.log(this.movement)
          }
        },err =>{
          console.log(err)
        }
      );
    }

  }



}

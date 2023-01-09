
import { Component, Injectable, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { observable ,Subject, Subscription } from 'rxjs';
import { Client } from 'src/app/client/models/client';
import { __values } from 'tslib';
import { Product } from '../../models/product';
import { ProductServices } from '../../services/product.services';


@Component({
  selector: 'app-product-get',
  templateUrl: './product-get.component.html',
  styleUrls: ['./product-get.component.css']
})



export class ProductGetComponent implements OnInit {
  
  clienteId: number;
  product:Product[] =[];
  
  
  client:Client ;


  id_Type:string; //<-
  idNum:number;  // <-
  fisrtName:string ; //<-
  lastName:string ; //<-
  email:string; //<-
  birthdate:Date; //<-
  userCreation:string;
  datecreation:Date;
  dateudpate:Date;
  userUpdate:string;

  
  //Values Update
  triggerUpdateClient: boolean ;

  load:boolean= false;


  suscription:Subscription;
  constructor(private productService: ProductServices, private route: ActivatedRoute, private router: Router){

    route.params.subscribe(val => {
      // put the code from `ngOnInit` here
      this.ngOnInit();
    });

   }

   ActiveChangeDataClient():void
   {
if(this.triggerUpdateClient == false)
this.triggerUpdateClient = true;
else
this.triggerUpdateClient = false;

   }
  
  ngOnInit(): void {
    this.clienteId = this.route.snapshot.params['id'];    

    this.loadClient();
    this.loadProduct();
    this.triggerUpdateClient = false;
    
    this.suscription = this.productService.refresh$.subscribe(()=>{
      this.loadProduct();
      // this.loadClient();

    });
    
  }
  refreshComponent():void{
    this.router.navigate([this.router.url])
 }

 reloadCurrentPage() {
  window.location.reload();
 }


  loadProduct():void{
    this.productService.ProductByIdClient( this.clienteId).subscribe(
      (response) =>{
        if(response.peticionExitosa){
          this.product = response.datos;  
          // this.reloadCurrentPage();
        }
      },err =>{
        console.log(err)
      }
    );
  }

  DeleteProduct(productId: number):void{
    
   
    this.productService.deleteProduct( productId).subscribe(
      (response) =>{
        if(response.peticionExitosa){
          // this.product = response.datos;    
          console.log("Porduct was canceled")  
        }
      },err =>{
        console.log(err)
      }
    );
  }


  ChangeStatus(productId: number , StatusName: string):void{
    this.productService.UpdateStatus(productId, StatusName ).subscribe(
      (response) =>{
        if(response.peticionExitosa){
          // this.product = response.datos;  
          console.log("Porduct status was changed")  
        }
      },err =>{
        console.log(err)
      }
    );
  }
//client
loadClient():void{
  this.productService.userDataById( this.clienteId).subscribe(
    (response) =>{
      if(response.peticionExitosa){
        this.client = response.datos;  
        this.load = true;
      }
    },err =>{
      console.log(err)
    }
  );
}

updateCliente():void{

  const newclient = new  Client(this.id_Type,
                            this.idNum,
                            this.fisrtName, 
                            this.lastName, 
                            this.email, 
                            this.birthdate,  
                            this.client.userCreation,
                            // this.client.datecreation,
                            "Admin" 
                            );
        //only way to set the var
        newclient.datecreation = this.client.datecreation; 
        

  this.productService.updateClient( this.clienteId, newclient).subscribe(
    (response) =>{
      if(response.peticionExitosa){
        this.client = response.datos;  
      }
    },err =>{
      console.log(err)
    }
  );



}

uploadDatauser():void{
this.updateCliente();
this.ActiveChangeDataClient();
this.loadClient();
}


}



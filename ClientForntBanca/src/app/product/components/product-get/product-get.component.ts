
import { Component, Injectable, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { observable ,Subject, Subscription } from 'rxjs';
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

  suscription:Subscription;
  constructor(private productService: ProductServices, private route: ActivatedRoute, private router: Router){

    route.params.subscribe(val => {
      // put the code from `ngOnInit` here
      this.ngOnInit();
    });

   }
  
  ngOnInit(): void {
    this.clienteId = this.route.snapshot.params['id'];    

    this.loadProduct();

    this.suscription = this.productService.refresh$.subscribe(()=>{
      this.loadProduct();

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
    
    // console.log(id);
    this.productService.deleteProduct( productId).subscribe(
      (response) =>{
        if(response.peticionExitosa){
          this.product = response.datos;
          console.log(this.product);
        
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
          this.product = response.datos;  
          // this.reloadCurrentPage();
        }
      },err =>{
        console.log(err)
      }
    );
  }


}



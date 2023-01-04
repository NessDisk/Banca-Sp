
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
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


  constructor(private productService: ProductServices, private route: ActivatedRoute){ }
  
  ngOnInit(): void {
    this.clienteId = this.route.snapshot.params['id'];    

    this.loadClient();
    
  }


  loadClient():void{
    this.productService.ProductByIdClient( this.clienteId).subscribe(
      (response) =>{
        if(response.peticionExitosa){
          this.product = response.datos;

        }
      },err =>{
        console.log(err)
      }
    );
  }
  

}



import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from '../../models/product';
import { ProductServices } from '../../services/product.services';
import { Location } from '@angular/common';

@Component({
  selector: 'app-product-create',
  templateUrl: './product-create.component.html',
  styleUrls: ['./product-create.component.css']
})
export class ProductCreateComponent implements OnInit {
  
  
  
  typeAccount: string;
  noAccount: number;
  clienteId: number;

 
  constructor(private productService: ProductServices, private route: ActivatedRoute, private _location: Location){ }

  
  goBack(){
    this._location.back();
  }
 
  ngOnInit(): void {
      this.clienteId = this.route.snapshot.params['id'];
      // this.loadClient();    
                  }

                  productCreate():void{
                    const product = new  Product(this.typeAccount,this.noAccount,"activo",10,this.clienteId);
                    this.productService.saveProduct(product ).subscribe(
                      (response) =>{
                        if(response.peticionExitosa){
                          console.log(response.datos) ;
                
                        }
                      },err =>{
                        console.log(err)
                      }
                    );                   
                  }
                  
}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from '../../models/product';
import { ProductServices } from '../../services/product.services';
import { Location } from '@angular/common';

import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-create',
  templateUrl: './product-create.component.html',
  styleUrls: ['./product-create.component.css']
})
export class ProductCreateComponent implements OnInit {
  
  
  
  typeAccount: string;
  noAccount: number;
  clienteId: number;

 
  constructor(private productService: ProductServices, private route: ActivatedRoute, private _location: Location ,private toastr: ToastrService, private _router: Router){ }

  
  
  goBack(){
    setTimeout(() => {
      this._location.back();
    }, 20);
  }
 
  ngOnInit(): void {
      this.clienteId = this.route.snapshot.params['id'];
      // this.loadClient();    
                  }

                  productCreate():void{
                    const product = new  Product(this.typeAccount,"enabled",this.clienteId,false);
                   console.log(product);
                   
                    this.productService.saveProduct(product ).subscribe(
                      (response) =>{
                        if(response.peticionExitosa){
                          console.log(response.datos) ;    
                          this.toastr.success(response.mensaje);            
                        }
                      },err =>{
                        console.log(err)
                      }
                    );                   
                  }
                  
}

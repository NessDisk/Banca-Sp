import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GeneralResponse } from 'src/app/shared/models/general-response';
import { Product } from '../models/product';


@Injectable({
    providedIn: 'root'
})
export class ProductServices  {

    productUrl = 'http://localhost:8090/v0/api/Product';
    constructor(private httpClient: HttpClient){    }

//    this.productoURL+"?clienteId="+`${clienteId}`
    public ProductByIdClient(clienteId: number): Observable<GeneralResponse<Product[]>>{
        return this.httpClient.get<GeneralResponse<Product[]>>(this.productUrl+ "/get/"+ clienteId );
      }


      public saveProduct(product: Product): Observable<any> {
        return this.httpClient.post<any>(this.productUrl + "/create", product);
      }
}
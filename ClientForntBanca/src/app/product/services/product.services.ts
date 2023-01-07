import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable , Subject } from 'rxjs';
import { GeneralResponse } from 'src/app/shared/models/general-response';
import { Product } from '../models/product';
import { tap } from 'rxjs/operators';
import { Client } from 'src/app/client/models/client';


@Injectable({
    providedIn: 'root'
})
export class ProductServices  {

    productUrl = 'http://localhost:8090/v0/api/Product';
    clientUrl = "http://localhost:8090/v0/api/Client";


    private _refresh$ = new Subject<void>();
   
    public get refresh$() {
    return this._refresh$;
  }

    constructor(private httpClient: HttpClient){    }

//    this.productoURL+"?clienteId="+`${clienteId}`
    public ProductByIdClient(clienteId: number): Observable<GeneralResponse<Product[]>>{
        return this.httpClient.get<GeneralResponse<Product[]>>(this.productUrl+ "/get/"+ clienteId );
      }


      public saveProduct(product: Product): Observable<any> {
        return this.httpClient.post<any>(this.productUrl + "/create", product);
      }

      public deleteProduct(productId: number): Observable<any> {
        return this.httpClient.delete<any>(this.productUrl + "/delete/" + productId )
        .pipe(
          tap(()=> {
            this._refresh$.next();
          })
        )

      }

      public UpdateStatus(productid:number,StatusProductName:string): Observable<any> {
        return this.httpClient.put<any>(this.productUrl + "/Status/"+productid+"/"+StatusProductName, {} ) .pipe(
          tap(()=> {
            this._refresh$.next();
          })
        )
      }

      public userDataById(clienteId:number): Observable<GeneralResponse<Client>> {
        return this.httpClient.get<GeneralResponse<Client>>(this.clientUrl +"/"+clienteId );
      }

      public updateClient(clientId:number,client:Client): Observable<any> {
        return this.httpClient.put<any>(this.clientUrl+"/" +clientId+"/update", client) .pipe(
          tap(()=> {
            this._refresh$.next();
          })
        )
      }
}
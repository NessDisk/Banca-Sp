import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GeneralResponse } from 'src/app/shared/models/general-response';
import { Movement } from '../models/moviment';

@Injectable({
    providedIn: 'root'
})

export  class MovementServices{
   MovUrl = 'http://localhost:8090/v0/api/transaction';
   constructor(private httpClient: HttpClient){    }

   //get
   public MovementByIdProduct(productId: number): Observable<GeneralResponse<Movement[]>>{
    return this.httpClient.get<GeneralResponse<Movement[]>>(this.MovUrl+ "/get/"+ productId );
  }


  public DespositByProductId(ProductId: number , movement: Movement ): Observable<any>{
    return this.httpClient.post<any>(this.MovUrl+ "/disposet/"+ ProductId ,  movement);
    }

  public WithdByProductId(ProductId: number , movement: Movement ): Observable<any>{
    return this.httpClient.post<any>(this.MovUrl+ "/withdraw/"+ ProductId ,  movement);
    }

    public Transfer(ProductId: number , movement: Movement ): Observable<any>{
        return this.httpClient.post<any>(this.MovUrl+ "/trasnfer/"+ ProductId ,  movement);
        }

}
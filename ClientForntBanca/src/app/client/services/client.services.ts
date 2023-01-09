import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { GeneralResponse } from 'src/app/shared/models/general-response';
import { Client } from '../models/client';
import { tap } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class ClienteService{

  private _refresh$ = new Subject<void>();
   
  public get refresh$() {
  return this._refresh$;
}

    clientUrl = 'http://localhost:8090/v0/api/Client'

    constructor(private httpClient: HttpClient)
    { }

    public listClient(): Observable<GeneralResponse<Client[]>>{
        return this.httpClient.get<GeneralResponse<Client[]>>(this.clientUrl);
      }


      public saveClient(client: Client): Observable<any> {
        return this.httpClient.post<any>(this.clientUrl + '', client);
      }

      public deleteClient(clientid: number): Observable<any> {
        return this.httpClient.delete<any>(this.clientUrl +"/"+clientid +'/delete').pipe(
          tap(()=> {
            this._refresh$.next();
          })
        )
      }
}
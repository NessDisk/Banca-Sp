import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GeneralResponse } from 'src/app/shared/models/general-response';
import { Client } from '../models/client';

@Injectable({
    providedIn: 'root'
})
export class ClienteService{

    clientUrl = 'http://localhost:8090/v0/api/Client'
    constructor(private httpClient: HttpClient)
    { }

    public listClient(): Observable<GeneralResponse<Client[]>>{
        return this.httpClient.get<GeneralResponse<Client[]>>(this.clientUrl);
      }


      public saveClient(client: Client): Observable<any> {
        return this.httpClient.post<any>(this.clientUrl + '', client);
      }
}
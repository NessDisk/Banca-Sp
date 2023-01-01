import { Component, OnInit } from '@angular/core';
import { GeneralResponse } from 'src/app/shared/models/general-response';
import { Client } from '../../models/client';
import { ClienteService } from '../../services/client.services';

@Component({
  selector: 'app-client-get-by-id',
  templateUrl: './client-get-by-id.component.html',
  styleUrls: ['./client-get-by-id.component.css']
})
export class ClientGetByIDComponent implements OnInit {

client : Client[] = [];

constructor(private clientservices: ClienteService){}

ngOnInit(): void {
  this.loadClient();
}

loadClient():void{
  this.clientservices.listClient().subscribe(
    (response) =>{
      if(response.peticionExitosa){
        this.client = response.datos;
      }
    },err =>{
      console.log(err)
    }
  );
}

}

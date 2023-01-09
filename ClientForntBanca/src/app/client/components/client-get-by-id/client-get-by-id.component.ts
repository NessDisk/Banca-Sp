import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
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

suscription:Subscription;
constructor(private clientservices: ClienteService, private route: ActivatedRoute){


  route.params.subscribe(val => {
    // put the code from `ngOnInit` here
    this.ngOnInit();
  });
}

ngOnInit(): void {
  this.loadClient();


  // route.params.subscribe(val => {
  //   // put the code from `ngOnInit` here
  //   this.ngOnInit();
  // });
  this.suscription = this.clientservices.refresh$.subscribe(()=>{
    this.loadClient();
    

  });

}

loadClient():void{
  this.clientservices.listClient().subscribe(
    (response) =>{
      if(response.peticionExitosa){
        this.client = response.datos;
        console.log(this.client);
      }
    },err =>{
      console.log(err)
    }
  );
}

DeleteCliente(id: number):void{

  console.log();
  this.clientservices.deleteClient(id).subscribe(
    (response) =>{
      if(response.peticionExitosa){
        this.client = response.datos;
        console.log(response.peticionExitosa);
      }
    },err =>{
      console.log(err)
    }
  );
}

}

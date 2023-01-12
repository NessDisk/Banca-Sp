import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { GeneralResponse } from 'src/app/shared/models/general-response';
import { Client } from '../../models/client';
import { ClienteService } from '../../services/client.services';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-client-get-by-id',
  templateUrl: './client-get-by-id.component.html',
  styleUrls: ['./client-get-by-id.component.css']
})
export class ClientGetByIDComponent implements OnInit {

client : Client[] = [];

suscription:Subscription;
constructor(private clientservices: ClienteService,
           private route: ActivatedRoute, 
           private toastr: ToastrService, private _router: Router
  ){


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
        // console.log(this.client);
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
        // this.client = response.datos;
        console.log();
        if(response.mensaje == "0 - Client successfully delete")
        this.toastr.success(response.mensaje);
        else
        this.toastr.info(response.mensaje);
      }
    },err =>{
      console.log(err)
      this.toastr.info(err);
    }
  );
}

}

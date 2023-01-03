import { getLocaleDateTimeFormat } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Client } from '../../models/client';
import { ClienteService } from '../../services/client.services';

@Component({
  selector: 'app-client-create',
  templateUrl: './client-create.component.html',
  styleUrls: ['./client-create.component.css']
})
export class ClientCreateComponent implements OnInit {

  client : Client[] = [];

  ngOnInit(): void {
    // throw new Error('Method not implemented.');
  }

  id?:number;
  Tipo:string;
  CC:number;
  nombres:string;
  apellido:string;
  birDate:Date;
  age:number;
  date:Date;
  dateCreation:Date;
  dateudpate:Date;
  UserUpdate:string;


  constructor(private clientservices: ClienteService){}

  onCreate(): void{
    
     const client = new Client(this.Tipo,this.CC,this.nombres,this.apellido ,this.birDate,this.age, this.date,"Undefine");
    // this.date = new Date();
    //  const client = new Client("DEBITO",123456,"TEST NAME","Test last name" ,this.date,19, this.date,"user name");
    // console.log(client);
    this.clientservices.saveClient(client).subscribe(
      (response) =>{
        if(response.peticionExitosa){
          this.client = response.datos;
          console.log(this.client);
          console.log("The user has been successfully done.");
          }
        },err =>{
          console.log(err)
        }
      );
    
  }


}

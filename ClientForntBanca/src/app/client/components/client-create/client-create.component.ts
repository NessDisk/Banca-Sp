import { getLocaleDateTimeFormat } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
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
  id_Type:string; //<-
  idNum:number;  // <-
  fisrtName:string; //<-
  lastName:string; //<-
  email:string; //<-
  birthdate:Date; //<-
  userCreation:string;
  datecreation:Date;
  dateudpate:Date;
  userUpdate:string;


  
  constructor(private clientservices: ClienteService , private formBuilder: FormBuilder ){
    
    
  }
  
  createFormClient = this.formBuilder.group({
    email1:[ undefined, [ Validators.required ,
                          Validators.email ,
                          Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$") ]],
    name1:[undefined, [ Validators.required, Validators.minLength(2)]],
    name2:[undefined, [ Validators.required, Validators.minLength(2)]],
    idNum1:[undefined, [ Validators.required,]],
    idType:[undefined, [ Validators.required,]],
    date1:[undefined, [ Validators.required, ]],
  });


  onCreate(): void{
    
     const client = new Client(this.id_Type,this.idNum ,this.fisrtName, this.lastName, this.email, this.birthdate, "Admin","Admin" );
    // this.date = new Date();
    //  const client = new Client("DEBITO",123456,"TEST NAME","Test last name" ,this.date,19, this.date,"user name");
    console.log(client);
    this.clientservices.saveClient(client).subscribe(
      (response) =>{
        if(response.peticionExitosa){
          this.client = response.datos;
          console.log(this.client);
          console.log(response.mensaje);
          // console.log("The user has been successfully done.");
          }
        },err =>{
          console.log(err)
        }
      );
    
  }

  create():void{
    console.log("MENSAJE DE PRUEBA");

    console.log(this.createFormClient.value.email1);
    console.log(this.createFormClient.value.name1);
  }


}

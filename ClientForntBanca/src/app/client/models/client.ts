export class Client {
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

     constructor(Tipo:string, CC: number, nombres: string, apellido: string, birDate: Date, age:number, date: Date, /* dateCreation: Date, dateudpate: Date,*/ UserUpdate: string )
     {
          this.Tipo=Tipo;
          this.CC=CC;
          this.nombres=nombres;
          this.apellido=apellido;
          this.birDate=birDate;
          this.age=age;
          this.date=date;
          // this.dateCreation=dateCreation;
          // this.dateudpate=dateudpate;
          this.UserUpdate=UserUpdate;
     }
}
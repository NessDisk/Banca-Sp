export class Client {
     id?:number;
     id_Type :string;
     idNum: number;
     fisrtName:string;
     lastName:string;
     email:string;
     birthdate:Date;
     userCreation:number;
     datecreation:Date;
     dateudpate:Date;
     userUpdate:string;
     // UserUpdate:string;

     // constructor(Tipo:string, CC: number, nombres: string, apellido: string, birDate: Date, age:number, date: Date, /* dateCreation: Date, dateudpate: Date,*/ UserUpdate: string )
     // {
     //      this.id_Type =Tipo;
     //      this.fisrtName=CC;
     //      this.lastName=nombres;
     //      this.Email=apellido;
     //      this.birDate=birDate;
     //      this.age=age;
     //      this.date=date;
     //      // this.dateCreation=dateCreation;
     //      // this.dateudpate=dateudpate;
     //      this.UserUpdate=UserUpdate;
     // }

     constructor(
          id_Type :string,
          fisrtName:string,
          lastName:string,
          email:string,
          birthdate:Date,
          userCreation:number,
          datecreation:Date,
          dateudpate:Date,
          UserUpdate:string)
     {
          this.id_Type = id_Type;
          this.fisrtName =fisrtName;
          this.lastName =lastName;
          this.email = email;
          this.birthdate = birthdate;
          this.userCreation = userCreation;
          this.datecreation = datecreation;
          this.dateudpate = dateudpate;
          this.userUpdate = UserUpdate;
     }

     // constructor(
     //      id_Type :string,
     //      fisrtName:number,
     //      lastName:string,
     //      email:string,
     //      birthdate:Date,
     //      userCreation:number,
     //      datecreation:Date,
     //      dateudpate:Date,
     //      UserUpdate:Date)
     // {
     //      this.id_Type = id_Type;
     //      this.fisrtName =fisrtName;
     //      this.lastName =lastName;
     //      this.email = email;
     //      this.birthdate = birthdate;
     //      this.userCreation = userCreation;
     //      this.datecreation = datecreation;
     //      this.dateudpate = dateudpate;
     //      this.UserUpdate = UserUpdate;
     // }
}
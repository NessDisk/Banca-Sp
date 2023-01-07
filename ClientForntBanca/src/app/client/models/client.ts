export class Client {
     id?:number;
     id_Type :string;
     idNum: number;
     fisrtName:string;
     lastName:string;
     email:string;
     birthdate:Date;
     userCreation:string;
     datecreation:Date;
     dateudpate: Date;
     userUpdate:string;


     public  constructor(
          id_Type :string,
          idNum:number,
          fisrtName:string,
          lastName:string,
          email:string,
          birthdate:Date,
          userCreation:string,
          // datecreation:Date,
          // dateudpate:Date,
          UserUpdate:string
          )
     {
          this.id_Type = id_Type;
          this.idNum = idNum;
          this.fisrtName = fisrtName;
          this.lastName = lastName;
          this.email = email;
          this.birthdate = birthdate;
          this.userCreation = userCreation;
          // this.datecreation = datecreation;
          // this.dateudpate = dateudpate;
          this.userUpdate = UserUpdate;
     }

     
     public get_dateudpate(): Date {
          return this.dateudpate;
     }
     public Set_dateudpate(value: Date) {
          this.dateudpate = value;
     }


}
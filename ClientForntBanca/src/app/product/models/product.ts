export class Product{
    id:number;
    typeAccount:string;
    numAccont:number;
    state:string;
    dateCreate:Date;
    balance:number;
    balanceAvailable:number;
    excludeGMF: boolean;
    UserCreation:Date;
    dateUdpate:Date;
    clienteId:number;

    constructor(typeAccount:string,
        state:string,
        clienteId:number,
        excludeGMF: boolean){
            this.typeAccount=typeAccount;
            this.state=state;
            this.clienteId=clienteId;
            this.excludeGMF =excludeGMF ;
        }

}
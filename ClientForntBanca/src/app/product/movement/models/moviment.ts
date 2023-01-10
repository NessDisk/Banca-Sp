export class Movement{
id:number;
typeTransaction:String;
dateCreate: Date;
AccountId: number;
description: String;
typeDebito : String;
balance : number;
avaiableBalance : number;
initialBalance: number;
value: number;
finalBalance: number;
destinyAccount: number ;

constructor(
    typeTransaction:String,
   
    cuentaId: number,
    Description: String,
    valor: number,
    cuentaDestino: number
            )
{
    this.typeTransaction=typeTransaction;
    this.AccountId=cuentaId;
    this.description=Description;
    this.value=valor;
    this.destinyAccount=cuentaDestino;
}

}
export class Movement{
id:number;
typeTransaction:String;
dateCreate: Date;
cuentaId: number;
Description: String;
typeDebito : String;
Saldo : number;
SaldoDisponible : number;
saldoInicial: number;
valor: number;
saldoFinal: number;
cuentaDestino: number ;

constructor(
    typeTransaction:String,
   
    cuentaId: number,
    Description: String,
    valor: number,
    cuentaDestino: number
            )
{
    this.typeTransaction=typeTransaction;
    this.cuentaId=cuentaId;
    this.Description=Description;
    this.valor=valor;
    this.cuentaDestino=cuentaDestino;
}

}
class movement{
id:number;
typeTransaction:String;
DateCreate: Date;
cuentaId: number;
Descripcion: String;
TypeDébito : String;
Saldo : number;
SaldoDisponible : number;
saldoInicial: number;
valor: number;
saldoFinal: number;
cuentaDestino: number ;

constructor(
    typeTransaction:String,
    DateCreate: Date,
    cuentaId: number,
    Descripcion: String,
    TypeDébito : String,
    Saldo : number,
    SaldoDisponible : number,
    saldoInicial: number,
    valor: number,
    saldoFinal: number,
    cuentaDestino: number
            )
{
    this.typeTransaction=typeTransaction;
    this.DateCreate=DateCreate;
    this.cuentaId=cuentaId;
    this.Descripcion=Descripcion;
    this.TypeDébito=TypeDébito;
    this.Saldo=Saldo;
    this.SaldoDisponible=SaldoDisponible ;
    this.saldoInicial=saldoInicial;
    this.valor=valor;
    this.saldoFinal=saldoFinal;
    this.cuentaDestino=cuentaDestino;
}

}
export class Product{
    id:number;
    tipoCuenta:string;
    numeroCuenta:number;
    estado:string;
    fechaApertura:string;
    saldo:Date;
    saldoDisponible:number;
    exentaGMF:Date;
    UserCreation:Date;
    dateUdpate:Date;
    clienteId:string;

    constructor(tipoCuenta:string,
        numeroCuenta:number,
        estado:string,
        fechaApertura:string,
        saldo:Date,
        saldoDisponible:number,
        exentaGMF:Date,
        UserCreation:Date,
        dateUdpate:Date,
        clienteId:string){
            this.tipoCuenta=tipoCuenta;
            this.numeroCuenta=numeroCuenta;
            this.estado=estado;
            this.fechaApertura=fechaApertura;
            this.saldo=saldo;
            this.saldoDisponible=saldoDisponible;
            this.exentaGMF=exentaGMF;
            this.UserCreation=UserCreation;
            this.dateUdpate=dateUdpate;
            this.clienteId=clienteId;

        }

}
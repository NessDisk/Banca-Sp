export class Product{
    id:number;
    tipoCuenta:string;
    numeroCuenta:number;
    estado:string;
    fechaApertura:string;
    saldo:number;
    saldoDisponible:number;
    exentaGMF:Date;
    UserCreation:Date;
    dateUdpate:Date;
    clienteId:number;

    constructor(tipoCuenta:string,
        numeroCuenta:number,
        estado:string,
        saldo:number,    
        clienteId:number){
            this.tipoCuenta=tipoCuenta;
            this.numeroCuenta=numeroCuenta;
            this.saldo=saldo;
            this.estado=estado;
            this.clienteId=clienteId;

        }

}
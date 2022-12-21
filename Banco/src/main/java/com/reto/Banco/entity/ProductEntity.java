package com.reto.Banco.entity;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="productos")
public class ProductEntity {
    

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	


    @Column(name="tipo")
	private String tipoCuenta;
	
	@Column(name="numeroCuenta")
	private String numeroCuenta;
	
	
	@Column(name="estado")
	private String estado;
	
	@Column(name = "fecha_apertura")	
	private LocalDate fechaApertura;    
	
	


    @Column(name="saldo")
	private Double saldo;

    @Column(name="saldo_disponible")
	private Double saldoDisponible;

    @Column(name = "exentaGMF")
	private float exentaGMF;

	
	@Column(name = "UserCreation")
	private Long UserCreation;

    @Column(name="dateUdpate")
    private  Date dateUdpate;

    @Column(name = "cliente_id")
	private Long clienteId;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(Double saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    public float getExentaGMF() {
        return exentaGMF;
    }

    public void setExentaGMF(float exentaGMF) {
        this.exentaGMF = exentaGMF;
    }

    public Long getUserCreation() {
        return UserCreation;
    }

    public void setUserCreation(Long userCreation) {
        UserCreation = userCreation;
    }

    public Date getDateUdpate() {
        return dateUdpate;
    }

    public void setDateUdpate(Date dateUdpate) {
        this.dateUdpate = dateUdpate;
    }
   
}

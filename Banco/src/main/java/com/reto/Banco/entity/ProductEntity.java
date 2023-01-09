package com.reto.Banco.entity;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.cglib.core.Local;

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
	


    @Column(name="typeAccount")
	private String typeAccount;
	
	@Column(name="numAccont")
	private String numAccont;
	
	
	@Column(name="State")
	private String State;
	
	@Column(name = "DateCreate")	
	private LocalDate DateCreate;    

    @Column(name="balance")
	private Double balance;

    @Column(name="balanceAvailable")
	private Double balanceAvailable;

    @Column(name = "excludeGMF")
	private boolean excludeGMF;

	
	@Column(name = "userCreation")
	private String userCreation;




    @Column(name="dateUdpate")
    private  LocalDate dateUdpate;

    @Column(name = "clienteId")
	private Long clienteId;

    public Long getClienteId() {
        return clienteId;
    }

    public ProductEntity(String typeAccount, String numAccont, String state, LocalDate dateCreate, Double balance,
    Double balanceAvailable, Boolean gMF, String userCreation, LocalDate dateUdpate, Long clienteId) {
this.typeAccount = typeAccount;
this.numAccont = numAccont;
this.State = state;
this.DateCreate = dateCreate;
this.balance = balance;
this.balanceAvailable = balanceAvailable;
this.excludeGMF = gMF;
this.userCreation = userCreation;
this.dateUdpate = dateUdpate;
this.clienteId = clienteId;
}



public ProductEntity() {}

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeAccount() {
        return typeAccount;
    }

    public void setTypeAccount(String tipoCuenta) {
        this.typeAccount = tipoCuenta;
    }

    public String getNumAccont() {
        return numAccont;
    }

    public void setNumAccont(String numeroCuenta) {
        this.numAccont = numeroCuenta;
    }

    public String getState() {
        return State;
    }

    public void setState(String estado) {
        this.State = estado;
    }

    public LocalDate getDateCreate() {
        return DateCreate;
    }

    public void setDateCreate(LocalDate fechaApertura) {
        this.DateCreate = fechaApertura;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double saldo) {
        this.balance = saldo;
    }

    public Double getBalanceAvailable() {
        return balanceAvailable;
    }

    public void setBalanceAvailable(Double saldoDisponible) {
        this.balanceAvailable = saldoDisponible;
    }

    public boolean getExcludeGMF() {
        return excludeGMF;
    }

    public void setGMF(boolean exentaGMF) {
        this.excludeGMF = exentaGMF;
    }
    

    
    public String getUserCreation() {
        return userCreation;
    }

    public void setUserCreation(String userCreation) {
        this.userCreation = userCreation;
    }

    public LocalDate getDateUdpate() {
        return dateUdpate;
    }

    public void setDateUdpate(LocalDate dateUdpate) {
        this.dateUdpate = dateUdpate;
    }
   
}

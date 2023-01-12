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
@Table(name="Transaction")
public class TransactionEntity {

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

   

	@Column(name="typeTransaction")
	private String typeTransaction;
	

	@Column(name="dateCreate")
	private LocalDate dateCreate;

	@Column(name = "accountId")
	private long accountId;

	@Column(name="description")
	private String description;

	//débito or  credict
	@Column(name="typeDebito")
	private String typeDebito;

	@Column(name="balance")
	private double balance;

	@Column(name="AvaiableBalance")
	private double avaiableBalance;

	@Column(name = "initialBalance")	
	private double initialBalance;
	
	@Column(name = "value")
	private double value;
	
	@Column(name = "finalBalance")	
	private double finalBalance;

	@Column(name="destinyAccount")
	private long destinyAccount;

	

	public TransactionEntity(String typeTransaction, LocalDate dateCreate, long AccountId, String descripcion,
	String typeDébito, double balance, double avaiableBalance, double initialBalance, double value,
	double finalBalance, long cuentaDestino) {
			this.typeTransaction = typeTransaction;
			this.dateCreate = dateCreate;
			this.accountId = AccountId;
			this.description = descripcion;
			this.typeDebito = typeDébito;
			this.balance = balance;
			this.avaiableBalance = avaiableBalance;
			this.initialBalance = initialBalance;
			this.value = value;
			this.finalBalance = finalBalance;
			this.destinyAccount = cuentaDestino;
												}

	public TransactionEntity() {}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeTransaction() {
		return typeTransaction;
	}

	public void setTypeTransaction(String typeTransaction) {
		this.typeTransaction = typeTransaction;
	}

	public LocalDate getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(LocalDate dateCreate) {
		this.dateCreate = dateCreate;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTypeDebito() {
		return typeDebito;
	}

	public void setTypeDebito(String typeDebito) {
		this.typeDebito = typeDebito;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getAvaiableBalance() {
		return avaiableBalance;
	}

	public void setAvaiableBalance(double avaiableBalance) {
		this.avaiableBalance = avaiableBalance;
	}

	public double getInitialBalance() {
		return initialBalance;
	}

	public void setInitialBalance(double initialBalance) {
		this.initialBalance = initialBalance;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getFinalBalance() {
		return finalBalance;
	}

	public void setFinalBalance(double finalBalance) {
		this.finalBalance = finalBalance;
	}

	public long getDestinyAccount() {
		return destinyAccount;
	}

	public void setDestinyAccount(long destinyAccount) {
		this.destinyAccount = destinyAccount;
	}


}

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
	

	@Column(name="date_create")
	private LocalDate dateCreate;

	@Column(name = "cuenta_id")
	private long cuentaId;

	@Column(name="description")
	private String Description;

	//débito or  crédito
	@Column(name="Type_Debito")
	private String typeDebito;

	@Column(name="Saldo")
	private double Saldo;

	@Column(name="SaldoDisponible")
	private double SaldoDisponible;

	@Column(name = "saldo_inicial")	
	private double saldoInicial;
	
	@Column(name = "valor")
	private double valor;
	
	@Column(name = "saldo_final")	
	private double saldoFinal;

	@Column(name="cuenta_destino")
	private long cuentaDestino;

	public TransactionEntity(String typeTransaction, LocalDate dateCreate, long cuentaId, String descripcion,
	String typeDébito, double saldo, double saldoDisponible, double saldoInicial, double valor,
	double saldoFinal, long cuentaDestino) {
			this.typeTransaction = typeTransaction;
			this.dateCreate = dateCreate;
			this.cuentaId = cuentaId;
			Description = descripcion;
			typeDebito = typeDébito;
			Saldo = saldo;
			SaldoDisponible = saldoDisponible;
			this.saldoInicial = saldoInicial;
			this.valor = valor;
			this.saldoFinal = saldoFinal;
			this.cuentaDestino = cuentaDestino;
												}

	public TransactionEntity() {}

	
	public String getTypeDebito() {
		return typeDebito;
	}

	public void setTypeDebito(String typeDébito) {
		typeDebito = typeDébito;
	}

	public long getCuentaId() {
		return cuentaId;
	}

	public void setCuentaId(long cuentaId) {
		this.cuentaId = cuentaId;
	}

	public long getCuentaDestino() {
		return cuentaDestino;
	}

	public void setCuentaDestino(long cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}

	public double getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getSaldoFinal() {
		return saldoFinal;
	}

	public void setSaldoFinal(double saldoFinal) {
		this.saldoFinal = saldoFinal;
	}

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

	public String getDescription() {
		return Description;
	}

	public void setDescription(String descripcion) {
		Description = descripcion;
	}

	public double getSaldo() {
		return Saldo;
	}

	public void setSaldo(Double saldo) {
		Saldo = saldo;
	}

	public double getSaldoDisponible() {
		return SaldoDisponible;
	}

	public void setSaldoDisponible(double saldoDisponible) {
		SaldoDisponible = saldoDisponible;
	}
}

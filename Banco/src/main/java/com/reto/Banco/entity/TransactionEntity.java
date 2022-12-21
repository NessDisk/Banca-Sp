package com.reto.Banco.entity;

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
	

	@Column(name="DateCreate")
	private LocalDate DateCreate;

	@Column(name = "cuenta_id")
	private long cuentaId;

	@Column(name="Descripcion")
	private String Descripcion;

	//débito or  crédito
	@Column(name="TypeDébito")
	private String TypeDébito;

	@Column(name="Saldo")
	private double Saldo;

	@Column(name="SaldoDisponible")
	private String SaldoDisponible;

	@Column(name = "saldo_inicial")	
	private double saldoInicial;
	
	@Column(name = "valor")
	private double valor;
	
	@Column(name = "saldo_final")	
	private double saldoFinal;

	@Column(name="cuenta_destino")
	private long cuentaDestino;

	
	public String getTypeDébito() {
		return TypeDébito;
	}

	public void setTypeDébito(String typeDébito) {
		TypeDébito = typeDébito;
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
		return DateCreate;
	}

	public void setDateCreate(LocalDate dateCreate) {
		DateCreate = dateCreate;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public double getSaldo() {
		return Saldo;
	}

	public void setSaldo(Double saldo) {
		Saldo = saldo;
	}

	public String getSaldoDisponible() {
		return SaldoDisponible;
	}

	public void setSaldoDisponible(String saldoDisponible) {
		SaldoDisponible = saldoDisponible;
	}
}

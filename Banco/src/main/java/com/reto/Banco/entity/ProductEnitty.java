package com.reto.Banco.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class ProductEnitty {
    

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
	//@Temporal(TemporalType.DATE)
	private LocalDate fechaApertura;
	
	
	@Column(name="saldo")
	private Double saldo;
	
	@Column(name = "cliente_id")
	private Long clienteId;
}

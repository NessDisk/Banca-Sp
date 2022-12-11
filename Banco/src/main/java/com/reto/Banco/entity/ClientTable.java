package com.reto.Banco.entity;

import java.sql.Date;

import javax.swing.Spring;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name="Cliente")
public class ClientTable { 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    private String Tipo;    
    /* Card ID*/
    private  int CC;
   private  String nombres;
   private  String apellido;
   private  Date date ;
   private  Date dateCreation;
   private  Date dateudpate;
   private  String UserUpdate;
public long getId() {
    return id;
}
public void setId(Long id) {
    this.id = id;
}
public String getTipo() {
    return Tipo;
}
public void setTipo(String tipo) {
    Tipo = tipo;
}
public int getCC() {
    return CC;
}
public void setCC(int cC) {
    CC = cC;
}
public String getNombres() {
    return nombres;
}
public void setNombres(String nombres) {
    this.nombres = nombres;
}
public String getApellido() {
    return apellido;
}
public void setApellido(String apellido) {
    this.apellido = apellido;
}
public Date getDate() {
    return date;
}
public void setDate(Date date) {
    this.date = date;
}
public Date getDateCreation() {
    return dateCreation;
}
public void setDateCreation(Date dateCreation) {
    this.dateCreation = dateCreation;
}
public Date getDateudpate() {
    return dateudpate;
}
public void setDateudpate(Date dateudpate) {
    this.dateudpate = dateudpate;
}
public String getUserUpdate() {
    return UserUpdate;
}
public void setUserUpdate(String userUpdate) {
    UserUpdate = userUpdate;
}



}

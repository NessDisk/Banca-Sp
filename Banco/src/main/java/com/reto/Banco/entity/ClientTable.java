package com.reto.Banco.entity;


import java.time.LocalDate;
import java.util.Date;

import javax.print.DocFlavor.STRING;

import jakarta.persistence.Column;
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



    @Column(name="Id_Type")
    private String id_Type;    

    @Column(name="IdNum")
    private  int idNum;

    @Column(name="FisrtName")
    private  String fisrtName;

    @Column(name="lastName")
    private  String lastName;

    @Column(name="Email")
    private  String email ;

    @Column(name="birthdate")
    private  Date birthdate;

    private  String auxBirthdate;

    

    @Column(name="UserCreation")
     private  String userCreation;

    @Column(name="dateCreation")
     private  LocalDate datecreation;


     
    @Column(name="dateudpate")
    private  Date dateudpate;

    @Column(name="UserUpdate")
    private  String userUpdate;




public ClientTable(String id_Type, int idNum, String fisrtName, String lastName, String email, Date birthdate,
            String userCreation, LocalDate datecreation, Date dateudpate, String userUpdate) {
        
        this.id_Type = id_Type;
        this.idNum = idNum;
        this.fisrtName = fisrtName;
        this.lastName = lastName;
        this.email = email;
        this.birthdate = birthdate;
        this.userCreation = userCreation;
        this.datecreation = datecreation;
        this.dateudpate = dateudpate;
        this.userUpdate = userUpdate;

    }


public String getEmail() {
    return email;
}
public void setEmail(String email) {
    this.email = email;
}


public Date getBirthdate() {
    return birthdate;
}
public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
}

public long getId() {
    return id;
}
public void setId(Long id) {
    this.id = id;
}
public String getId_Type() {
    return id_Type;
}
public void setId_Type(String tipo) {
    id_Type = tipo;
}
public int getIdNum() {
    return idNum;
}
public void setIdNum(int cC) {
    idNum = cC;
}
public String getFisrtName() {
    return fisrtName;
}
public void setFisrtName(String nombres) {
    this.fisrtName = nombres;
}
public String getLastName() {
    return lastName;
}
public void setLastName(String apellido) {
    this.lastName = apellido;
}


public String getUserCreation() {
    return userCreation;
}
public void setUserCreation(String userCreation) {
    this.userCreation = userCreation;
}
public LocalDate getDatecreation() {
    return datecreation;
}
public void setDatecreation(LocalDate dateCreation) {
    this.datecreation = dateCreation;
}
public Date getDateudpate() {
    return dateudpate;
}
public void setDateudpate(Date dateudpate) {
    this.dateudpate = dateudpate;
}
public String getUserUpdate() {
    return userUpdate;
}
public void setUserUpdate(String userUpdate) {
    this.userUpdate = userUpdate;
}

public String getAuxBirthdate() {
    return auxBirthdate;
}


public void setAuxBirthdate(String auxBirthdate) {
    this.auxBirthdate = auxBirthdate;
}



}

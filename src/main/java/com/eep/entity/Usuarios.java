package com.eep.entity;

import javax.persistence.*;

@Entity
@Table(name="usuarios")
public class Usuarios {

    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;

    private String nombreusuario;

    private String password;

    private String tipo;//admin o normal

    public Usuarios() {
    }

    public Usuarios(int id, String nombreusuario, String password, String tipo) {
        this.id = id;
        this.nombreusuario = nombreusuario;
        this.password = password;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

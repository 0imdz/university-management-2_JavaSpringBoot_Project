package com.eep.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="alumno")
public class Alumno {

    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;

    @NotEmpty(message = "El nombre no puede quedar vacío")
    private String nombre;

    @NotEmpty(message = "Los apellidos no pueden quedar vacíos")
    private String apellidos;

    @NotEmpty(message = "La dirección no puede quedar vacía")
    private String direccion;

    @NotEmpty(message = "La localidad no puede quedar vacía")
    private String localidad;

    @NotEmpty(message = "La provincia no puede quedar vacía")
    private String provincia;

    @NotEmpty(message = "El país no puede quedar vacío")
    private String pais;

    @NotEmpty(message = "El teléfono no puede quedar vacío")
    @Pattern(regexp = "\\s?(?:6[0-9]|7[1-9])[0-9]\\s?[0-9]{3}\\s?[0-9]{3}$",message="Introduzca su número de teléfono sin espacios, por favor")
    private String telefono;

    private String asignaturaMatricula;

    public Alumno() {
    }

    public Alumno(int id, String nombre, String apellidos, String direccion, String localidad, String provincia, String pais, String telefono, String asignaturaMatricula) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
        this.pais = pais;
        this.telefono = telefono;
        this.asignaturaMatricula = asignaturaMatricula;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getAsignaturaMatricula() {
        return asignaturaMatricula;
    }

    public void setAsignaturaMatricula(String asignaturaMatricula) {
        this.asignaturaMatricula = asignaturaMatricula;
    }
}

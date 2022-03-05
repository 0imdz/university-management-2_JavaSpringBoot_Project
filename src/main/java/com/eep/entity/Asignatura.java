package com.eep.entity;

public class Asignatura {

    private int id;
    private String nombre;
    private String curso;
    private int horas;
    private String cuatrimestre;

    public Asignatura() {
    }

    public Asignatura(int id, String nombre, String curso, int horas, String cuatrimestre) {
        this.id = id;
        this.nombre = nombre;
        this.curso = curso;
        this.horas = horas;
        this.cuatrimestre = cuatrimestre;
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

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public String getCuatrimestre() {
        return cuatrimestre;
    }

    public void setCuatrimestre(String cuatrimestre) {
        this.cuatrimestre = cuatrimestre;
    }
}

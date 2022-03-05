package com.eep.service;


import com.eep.entity.Alumno;
import com.eep.entity.Asignatura;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface AlumnoService {

    //LISTAR
    public abstract List<Alumno> listarAlumnos();

    //AÑADIR
    public abstract Alumno addAlumno(Alumno alumno);

    //ELIMINAR
    public abstract List<Integer> listAlumnosParaBorrar(ArrayList<Integer> alumnosSeleccionados);

    //MODIFICAR

    //BUSCAR PARA MODIFICAR
    public abstract Optional<Alumno> buscarAlumno(int id);

    //EJECUTAR MODIFICACIÓN
    public abstract Alumno updateAlumno(Alumno alumno);

    //AÑADIR ASIGNATURAS A ALUMNO (DEVUELVE UN ALUMNO CON LAS ASIGNATURAS YA SELECCIONADAS)
    public Alumno anyadirAsignaturas(ArrayList<Integer> asignaturasSeleccion, Alumno alumno);

    //BORRADO ÚNICO
    public void borradoUnico(Alumno alumno);
}

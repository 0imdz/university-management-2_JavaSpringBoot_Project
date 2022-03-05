package com.eep.service;

import com.eep.entity.Asignatura;

import java.util.ArrayList;
import java.util.List;

public interface AsignaturaService {

    //LISTAR
    public abstract List<Asignatura> listarAsignaturas();

    //AÑADIR
    public abstract void anyadirAsignatura(Asignatura asignatura);

    //ELIMINAR
    public abstract void eliminarAsignaturas(ArrayList<Integer> asignaturasSeleccionadas);

    //MODIFICAR

    //BUSCAR PARA MODIFICAR
    public abstract Asignatura buscarAsignatura(int id);

    //EJECUTAR MODIFCACIÓN
    public abstract void modificarAsignatura(Asignatura asignatura);
}

package com.eep.service.impl;

import com.eep.entity.Alumno;
import com.eep.entity.Asignatura;
import com.eep.repository.AlumnoJPARepository;
import com.eep.service.AlumnoService;
import com.eep.service.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("alumnoServiceImpl")
public class AlumnoServiceImpl implements AlumnoService {

    List<Asignatura> asignaturasCompleto;

    List<Asignatura> asignaturasEscogidas;

    @Autowired
    @Qualifier("alumnoJpaRepository")
    private AlumnoJPARepository alumnoRepository;

    @Autowired
    @Qualifier("asignaturaServiceImpl")
    private AsignaturaService asignaturaService;

    //LISTAR

    @Override
    public List<Alumno> listarAlumnos() {
        return alumnoRepository.findAll();
    }

    //AÑADIR

    @Override
    public Alumno addAlumno(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    //ELIMINAR SELECCIONADOS

    @Override
    public List<Integer> listAlumnosParaBorrar(ArrayList<Integer> alumnosSeleccionados){

        for (int i=0; i<alumnosSeleccionados.size(); i++){
            alumnoRepository.deleteById(alumnosSeleccionados.get(i));
        }
        return alumnosSeleccionados;
    }

    //MODIFICAR

    //BUSCAR PARA MODIFICAR
    @Override
    public Optional<Alumno> buscarAlumno(int id){

        Optional<Alumno> alumnoEncontrado=alumnoRepository.findById(id);
        return alumnoEncontrado;
    }

    //EJECUTAR MODIFICACIÓN
    @Override
    public Alumno updateAlumno(Alumno alumno){
        return alumnoRepository.save(alumno);
    }

    //AÑADIR ASIGNATURAS
    @Override
    public Alumno anyadirAsignaturas(ArrayList<Integer> asignaturasSeleccion, Alumno alumno){

        int asignaturas[]=new int[asignaturasSeleccion.size()];

        String asignaturasCadena="";

        Alumno alumnoAdd=new Alumno();

        alumnoAdd.setId(alumno.getId());
        alumnoAdd.setNombre(alumno.getNombre());
        alumnoAdd.setApellidos(alumno.getApellidos());
        alumnoAdd.setDireccion(alumno.getDireccion());
        alumnoAdd.setLocalidad(alumno.getLocalidad());
        alumnoAdd.setProvincia(alumno.getProvincia());
        alumnoAdd.setPais(alumno.getPais());
        alumnoAdd.setTelefono(alumno.getTelefono());

        for (int i=0; i<asignaturasSeleccion.size(); i++){
            asignaturas[i]=asignaturasSeleccion.get(i);
        }

        //TRAIGO ASIGNATURAS DEL FICHERO Y LO METO EN ESTE NUEVO ARRAYLIST
        asignaturasCompleto = new ArrayList();
        asignaturasCompleto=asignaturaService.listarAsignaturas();//llamada al método que rellena este

        //CREO UN NUEVO ARRAYLIST PARA METER LAS ASIGNATURAS ESCOGIDAS
        asignaturasEscogidas=new ArrayList();

        for(int i=0; i<asignaturasCompleto.size(); i++){
            for(int j=0; j<asignaturas.length; j++){
                if(asignaturasCompleto.get(i).getId()==asignaturas[j]){
                    asignaturasEscogidas.add(asignaturasCompleto.get(i));
                }
            }
        }

        for(int i=0; i < asignaturasEscogidas.size();i++){
            if(i!=asignaturasEscogidas.size()-1){

                asignaturasCadena=asignaturasCadena+asignaturasEscogidas.get(i).getNombre();

                if(asignaturasEscogidas.get(i).getCurso().equals("Primero")){
                    asignaturasCadena=asignaturasCadena+"-1ºCURSO";
                }else if(asignaturasEscogidas.get(i).getCurso().equals("Segundo")){
                    asignaturasCadena=asignaturasCadena+"-2ºCURSO";
                }else if(asignaturasEscogidas.get(i).getCurso().equals("Tercero")){
                    asignaturasCadena=asignaturasCadena+"-3ºCURSO";
                }else if(asignaturasEscogidas.get(i).getCurso().equals("Cuarto")){
                    asignaturasCadena=asignaturasCadena+"-4ºCURSO";
                }

                if(asignaturasEscogidas.get(i).getCuatrimestre().equals("Primero")){
                    asignaturasCadena=asignaturasCadena+"-1ºCUAT";
                }else if(asignaturasEscogidas.get(i).getCuatrimestre().equals("Segundo")){
                    asignaturasCadena=asignaturasCadena+"-2ºCUAT";
                }

                asignaturasCadena=asignaturasCadena+", ";
            }else{
                asignaturasCadena=asignaturasCadena+asignaturasEscogidas.get(i).getNombre();

                if(asignaturasEscogidas.get(i).getCurso().equals("Primero")){
                    asignaturasCadena=asignaturasCadena+"-1ºCUR";
                }else if(asignaturasEscogidas.get(i).getCurso().equals("Segundo")){
                    asignaturasCadena=asignaturasCadena+"-2ºCUR";
                }else if(asignaturasEscogidas.get(i).getCurso().equals("Tercero")){
                    asignaturasCadena=asignaturasCadena+"-3ºCUR";
                }else if(asignaturasEscogidas.get(i).getCurso().equals("Cuarto")){
                    asignaturasCadena=asignaturasCadena+"-4ºCUR";
                }

                if(asignaturasEscogidas.get(i).getCuatrimestre().equals("Primero")){
                    asignaturasCadena=asignaturasCadena+"-1ºCUAT";
                }else if(asignaturasEscogidas.get(i).getCuatrimestre().equals("Segundo")){
                    asignaturasCadena=asignaturasCadena+"-2ºCUAT";
                }
            }
        }

        alumnoAdd.setAsignaturaMatricula(asignaturasCadena);

        return alumnoAdd;
    }

    @Override
    public void borradoUnico(Alumno alumno){
        alumnoRepository.delete(alumno);
    }
}

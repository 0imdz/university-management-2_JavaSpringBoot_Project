package com.eep.service.impl;

import com.eep.entity.Asignatura;
import com.eep.service.AsignaturaService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service("asignaturaServiceImpl")
public class AsignaturaServiceImpl implements AsignaturaService {

    ArrayList<Asignatura> asignaturas;
    ArrayList<Integer> identificadores;
    ArrayList<Integer> identificadoresOrdenados;

    //LISTAR ASIGNATURAS
    @Override
    public List<Asignatura> listarAsignaturas() {

        File f = null;
        FileReader ficheroLeer = null;
        BufferedReader ficheroBuf = null;

        try {
            f = new File("src\\main\\resources\\asignaturas.txt");
            ficheroLeer = new FileReader(f);
            ficheroBuf = new BufferedReader(ficheroLeer);
            asignaturas = new ArrayList();
            identificadores = new ArrayList();
            identificadoresOrdenados = new ArrayList();

            String linea;

            while ((linea = ficheroBuf.readLine()) != null) {

                String partes[] = linea.split("#");

                int id = Integer.parseInt(partes[0]);
                String nombre = partes[1];
                String curso = partes[2];
                int horas = Integer.parseInt(partes[3]);
                String cuatrimestre = partes[4];

                Asignatura asignatura = new Asignatura(id, nombre, curso, horas, cuatrimestre);

                asignaturas.add(asignatura);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("No se puede leer el archivo." + e.getMessage());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("No se puede leer el archivo." + e.getMessage());
        } finally {
            try {
                if (ficheroLeer != null) {
                    ficheroLeer.close();
                    System.out.println();
                    System.out.println("El programa fue cerrado exitosamente.");
                    return asignaturas;
                }
            } catch (IOException e) {
                System.out.println("No se puede cerrar el archivo." + e.getMessage());
            }
        }
        return asignaturas;
    }

    //AÑADIR ASIGNATURA
    @Override
    public void anyadirAsignatura(Asignatura asignatura) {

        listarAsignaturas();

        try {
            FileWriter fw = new FileWriter("src\\main\\resources\\asignaturas.txt", true);

            int generado=0;

            if(asignaturas.size()==0){
                generado=0;
            }else{
                for (int i=0; i<asignaturas.size(); i++){
                    identificadores.add(asignaturas.get(i).getId());
                }
                for(int i=0; i < identificadores.size(); i++){
                    Collections.sort(identificadores);//ordeno arraylist
                    Collections.reverse(identificadores);//ordeno arraylist de forma descendente
                    identificadoresOrdenados.add(identificadores.get(i));//rellenar arraylist ordenado descendentemente
                    generado=identificadoresOrdenados.get(0)+1;
                }
            }

            fw.write(Integer.toString(generado));
            fw.write("#");
            fw.write(asignatura.getNombre());
            fw.write("#");
            fw.write(asignatura.getCurso());
            fw.write("#");
            fw.write(Integer.toString(asignatura.getHoras()));
            fw.write("#");
            fw.write(asignatura.getCuatrimestre());
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

//    @Override
//    public boolean checkCantidadAsignaturas(){
//
//        listarAsignaturas();
//
//        if(asignaturas.size()>=11){
//            return true;
//        }else{
//            return false;
//        }
//    }

    //ELIMINAR ASIGNATURAS
    @Override
    public void eliminarAsignaturas(ArrayList<Integer> asignaturasSeleccionadas) {

        listarAsignaturas();

        for(int i=0; i<asignaturas.size(); i++){
            for(int j=0; j<asignaturasSeleccionadas.size(); j++){
                if(asignaturas.get(i).getId()==asignaturasSeleccionadas.get(j)){
                    asignaturas.remove(i);
                }
            }
        }

        try {
            FileWriter fw = new FileWriter("src\\main\\resources\\asignaturas.txt");

            for(int i=0; i< asignaturas.size(); i++){
                fw.write(Integer.toString(asignaturas.get(i).getId()));
                fw.write("#");
                fw.write(asignaturas.get(i).getNombre());
                fw.write("#");
                fw.write(asignaturas.get(i).getCurso());
                fw.write("#");
                fw.write(Integer.toString(asignaturas.get(i).getHoras()));
                fw.write("#");
                fw.write(asignaturas.get(i).getCuatrimestre());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public Asignatura buscarAsignatura(int idControlador){

        listarAsignaturas();
        Asignatura asignatura = null;

        for(int i=0; i<asignaturas.size(); i++){
            if(asignaturas.get(i).getId()==idControlador){
                int id=asignaturas.get(i).getId();
                String nombre=asignaturas.get(i).getNombre();
                String curso=asignaturas.get(i).getCurso();
                int horas=asignaturas.get(i).getHoras();
                String cuatrimestre=asignaturas.get(i).getCuatrimestre();

                asignatura=new Asignatura(id, nombre, curso, horas, cuatrimestre);
            }
        }
        return asignatura;
    }

    @Override
    public void modificarAsignatura(Asignatura asignatura){

        listarAsignaturas();

        for (int i=0; i<asignaturas.size(); i++){
            if(asignaturas.get(i).getId()==asignatura.getId()){

                int id=asignatura.getId();
                String nombre=asignatura.getNombre();
                String curso=asignatura.getCurso();
                int horas=asignatura.getHoras();
                String cuatrimestre=asignatura.getCuatrimestre();

                asignatura=new Asignatura(id, nombre, curso, horas, cuatrimestre);

                asignaturas.remove(i);
                asignaturas.add(asignatura);
                break;
            }
        }

        try {
            FileWriter fw = new FileWriter("src\\main\\resources\\asignaturas.txt");

            for(int i=0; i< asignaturas.size(); i++){
                fw.write(Integer.toString(asignaturas.get(i).getId()));
                fw.write("#");
                fw.write(asignaturas.get(i).getNombre());
                fw.write("#");
                fw.write(asignaturas.get(i).getCurso());
                fw.write("#");
                fw.write(Integer.toString(asignaturas.get(i).getHoras()));
                fw.write("#");
                fw.write(asignaturas.get(i).getCuatrimestre());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

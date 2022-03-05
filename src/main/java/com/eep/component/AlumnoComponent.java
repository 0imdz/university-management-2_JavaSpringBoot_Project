package com.eep.component;

import com.eep.entity.Alumno;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Component("alumnoComponent")
public class AlumnoComponent {

    private static final Log LOG = LogFactory.getLog(AlumnoComponent.class);

    public void alumnoAnyadido(Alumno alumno){
        String horaActual = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

        LOG.info("ALTA - "+alumno.getNombre()+" | "+alumno.getApellidos()+" | "+alumno.getDireccion()+" | "+alumno.getLocalidad()+" | "+alumno.getProvincia()+" | "+alumno.getPais()+" | "+alumno.getTelefono()+" | "+alumno.getAsignaturaMatricula());

    }

}

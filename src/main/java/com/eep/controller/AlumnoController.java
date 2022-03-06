package com.eep.controller;

import com.eep.component.AlumnoComponent;
import com.eep.entity.Alumno;
import com.eep.entity.Usuarios;
import com.eep.service.AlumnoService;
import com.eep.service.AsignaturaService;
import com.eep.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/cuibitcoin")
public class AlumnoController {

    @Autowired
    @Qualifier("alumnoServiceImpl")
    private AlumnoService alumnoService;

    @Autowired
    @Qualifier("UsuariosService")
    private UsuariosService usuariosService;

    @Autowired
    @Qualifier("asignaturaServiceImpl")
    private AsignaturaService asignaturaService;

    @Autowired
    @Qualifier("alumnoComponent")
    private AlumnoComponent alumnoComponent;

    //INICIO

    @GetMapping("/inicio")
    public String inicio(Model model){

        if(usuariosService.permitirAcceso()==true){
            Usuarios usuario1=usuariosService.devolucionUsuarioCompletoNoUsernameAndPassword();
            model.addAttribute("usuario1", usuario1);
            return "inicioapto";
        }else{
            return "redirect:/cuibitcoin/iniciosesion";
        }
    }

    //CERRAR SESIÓN

    @GetMapping("/cerrarsesion")
    public String cerrarSesion(Model model){

        usuariosService.cerrarSesion();
        return "redirect:/cuibitcoin/iniciosesion";
    }

    //LISTAR

    @GetMapping("/mostrar")
    public ModelAndView mostrado(){

        if(usuariosService.permitirAcceso()==true){
            ModelAndView mav=new ModelAndView("mostrar");
            mav.addObject("listaAlumnos", alumnoService.listarAlumnos());

            //PARA QUE SE VEA EL NOMBRE DEL USUARIO EN EL NAV
            Usuarios usuario10=usuariosService.devolucionUsuarioCompletoNoUsernameAndPassword();
            mav.addObject("usuario10", usuario10);

            return mav;
        }else{
            ModelAndView mav2=new ModelAndView("redirect:/cuibitcoin/iniciosesion");
            return mav2;
        }
    }

    //AÑADIR

    //MOSTRAR FORMULARIO DE ALTA
    @GetMapping("/nuevoalumnoform")
    public String inicio(@ModelAttribute Alumno alumno,
                         Model model) {

        if(usuariosService.permitirAcceso()==true){
            model.addAttribute("alumno", new Alumno());

            //PARA MOSTRAR ASIGNATURAS
            model.addAttribute("listaAsignaturas", asignaturaService.listarAsignaturas());

            //PARA QUE SE VEA EL NOMBRE DEL USUARIO EN EL NAV
            Usuarios usuario10=usuariosService.devolucionUsuarioCompletoNoUsernameAndPassword();
            model.addAttribute("usuario10", usuario10);

            return "nuevoalumno";
        }else{
            return "redirect:/cuibitcoin/iniciosesion";
        }
    }

    //EJECUTAR ALTA
    @PostMapping("/nuevoalumno")
    public String nuevoalumno(@ModelAttribute("alumno") @Valid Alumno alumno,
                              BindingResult result,
                              @RequestParam(defaultValue="") ArrayList<Integer> asignaturasSeleccion,
                              Model model) {

        if(result.hasErrors()){
            //PARA QUE SE VEA EL NOMBRE DEL USUARIO EN EL NAV
            Usuarios usuario10=usuariosService.devolucionUsuarioCompletoNoUsernameAndPassword();
            model.addAttribute("usuario10", usuario10);

            //PARA MOSTRAR ASIGNATURAS
            model.addAttribute("listaAsignaturas", asignaturaService.listarAsignaturas());
            return "nuevoalumno";
        }

        if(usuariosService.permitirAcceso()==true){

                Alumno alumnoEnviado=alumnoService.anyadirAsignaturas(asignaturasSeleccion, alumno);
                alumnoService.addAlumno(alumnoEnviado);
                alumnoComponent.alumnoAnyadido(alumnoEnviado);
                return "redirect:/cuibitcoin/mostrar";

        }else{
            return "redirect:/cuibitcoin/iniciosesion";
        }
    }

    //ELIMINAR

    @PostMapping("/borrar")
    public String borrar(@RequestParam("alumnosSeleccionados")ArrayList<Integer> alumnosSeleccionados){

        if(usuariosService.permitirAcceso()==true){
            System.out.println(alumnosSeleccionados);
            alumnoService.listAlumnosParaBorrar(alumnosSeleccionados);

            return "redirect:/cuibitcoin/mostrar";
        }else{
            return "redirect:/cuibitcoin/iniciosesion";
        }
    }

    //MODIFICAR

    //BUSCAR PARA MODIFICAR
    @GetMapping("/buscaralumno/{id}")
    public String editarPorId(@PathVariable int id,
                              Model model) {

        if(usuariosService.permitirAcceso()==true){
            model.addAttribute("listaAsignaturas", asignaturaService.listarAsignaturas());

            Optional<Alumno> alumnoEnviado = alumnoService.buscarAlumno(id);//devolución de un alumno
            model.addAttribute("alumno", alumnoEnviado);

            //PARA QUE SE VEA EL NOMBRE DEL USUARIO EN EL NAV
            Usuarios usuario10=usuariosService.devolucionUsuarioCompletoNoUsernameAndPassword();
            model.addAttribute("usuario10", usuario10);
            return "updatealumno";
        }else{
            return "redirect:/cuibitcoin/iniciosesion";
        }
    }

    //EJECUTAR MODIFICACIÓN
    @PostMapping("/modificacionalumnorealizada")
    public String modificacionRealizada(@ModelAttribute("alumno") @Valid Alumno alumno,
                                        BindingResult result,
                                        Model model){

        if(result.hasErrors()){
            //PARA QUE SE VEA EL NOMBRE DEL USUARIO EN EL NAV
            Usuarios usuario10=usuariosService.devolucionUsuarioCompletoNoUsernameAndPassword();
            model.addAttribute("usuario10", usuario10);
            return "nuevoalumno";
        }

        if(usuariosService.permitirAcceso()==true){
            alumnoService.updateAlumno(alumno);
            return "redirect:/cuibitcoin/mostrar";
        }else{
            return "redirect:/cuibitcoin/iniciosesion";
        }
    }

    //MOSTRAR USUARIOS DADOS DE ALTA
    @GetMapping("/mostrarusuarios")
    public String mostrarusuarios(Model model){

        //PARA QUE SE VEA EL NOMBRE DEL USUARIO EN EL NAV
        Usuarios usuario10=usuariosService.devolucionUsuarioCompletoNoUsernameAndPassword();
        model.addAttribute("usuario10", usuario10);

        if(usuariosService.permitirAcceso()==true){
            model.addAttribute("listaUsuarios", usuariosService.mostrarUsuarios());
            return "mostrarusuarios";
        }else{
            return "redirect:/cuibitcoin/iniciosesion";
        }
    }

    @GetMapping("/borrarusuario/{id}")
    public String deleteCourse(@PathVariable int id){

        usuariosService.borrarUsuario(id);
        return "redirect:/cuibitcoin/mostrarusuarios";
    }
}

package com.eep.controller;

import com.eep.entity.Alumno;
import com.eep.entity.Asignatura;
import com.eep.entity.Usuarios;
import com.eep.service.AlumnoService;
import com.eep.service.AsignaturaService;
import com.eep.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class AsignaturaController {

    @Autowired
    @Qualifier("asignaturaServiceImpl")
    private AsignaturaService asignaturaService;

    @Autowired
    @Qualifier("UsuariosService")
    private UsuariosService usuariosService;

    //LISTAR

    @GetMapping("/mostrarasignaturas")
    public ModelAndView mostrado() {

        if (usuariosService.permitirAcceso() == true) {
            ModelAndView mav = new ModelAndView("mostrarasignaturas");
            mav.addObject("listaAsignaturas", asignaturaService.listarAsignaturas());

            //PARA QUE SE VEA EL NOMBRE DEL USUARIO EN EL NAV
            Usuarios usuario10 = usuariosService.devolucionUsuarioCompletoNoUsernameAndPassword();
            mav.addObject("usuario10", usuario10);
            return mav;
        }else {
            ModelAndView mav2=new ModelAndView("redirect:/cuibitcoin/iniciosesion");
            return mav2;
        }
    }

    //AÑADIR

    //MOSTRAR FORMULARIO DE ALTA
    @GetMapping("/nuevaasignaturaform")
    public String nuevaAsignatura(@ModelAttribute Asignatura asignatura,
                                  Model model) {

        if (usuariosService.permitirAcceso() == true) {
            model.addAttribute("asignatura", new Asignatura());

            //PARA QUE SE VEA EL NOMBRE DEL USUARIO EN EL NAV
            Usuarios usuario10 = usuariosService.devolucionUsuarioCompletoNoUsernameAndPassword();
            model.addAttribute("usuario10", usuario10);
            return "nuevaasignatura";
        } else {
            return "redirect:/cuibitcoin/iniciosesion";
        }
    }

    //EJECUTAR ALTA
    @PostMapping("/nuevaasignatura")
    public String nuevoasignatura(@ModelAttribute("asignatura") Asignatura asignatura) {

        asignaturaService.anyadirAsignatura(asignatura);
        return "redirect:/cuibitcoin/mostrarasignaturas";
    }

    //ELIMINAR

    @PostMapping("/borrarasignaturas")
    public String borrar(@RequestParam("asignaturasSeleccionadas") ArrayList<Integer> asignaturasSeleccionadas,
                         Model model) {

            asignaturaService.eliminarAsignaturas(asignaturasSeleccionadas);
            return "redirect:/cuibitcoin/mostrarasignaturas";
    }

    //MODIFICAR

    //BUSCAR PARA MODIFICAR
    @GetMapping("/busquedaasignatura/{id}")
    public String buscarAsignatura(@PathVariable int id,
                                   Model model) {

        Asignatura asignaturaEnviada = asignaturaService.buscarAsignatura(id);
        model.addAttribute("asignatura", asignaturaEnviada);

        //PARA QUE SE VEA EL NOMBRE DEL USUARIO EN EL NAV
        Usuarios usuario10 = usuariosService.devolucionUsuarioCompletoNoUsernameAndPassword();
        model.addAttribute("usuario10", usuario10);

        return "updateasignatura";
    }

    //EJECUTAR MODIFICACIÓN
    @PostMapping("/editar")
    public String editar(@ModelAttribute("asignatura") Asignatura asignatura) {

        asignaturaService.modificarAsignatura(asignatura);

        return "redirect:/cuibitcoin/mostrarasignaturas";
    }

}

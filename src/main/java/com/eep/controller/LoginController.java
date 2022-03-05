package com.eep.controller;

import com.eep.entity.Alumno;
import com.eep.entity.Usuarios;
import com.eep.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("/cuibitcoin")
public class LoginController {

    @Autowired
    @Qualifier("UsuariosService")
    private UsuariosService usuariosService;

    //REGISTRO

    //MOSTRAR FORMULARIO PARA ALTA
    @GetMapping("/registro")
    public String registro(Model model){
        model.addAttribute("usuarios", new Usuarios());
        return "registro";
    }

    //EJECUTAR ALTA
    @PostMapping("/nuevousuario")
    public String nuevousuario(@ModelAttribute("usuarios") Usuarios usuario,
                              Model model) {

        model.addAttribute("usuarios", usuariosService.addUsuario(usuario));
        return "redirect:/cuibitcoin/iniciosesion";
    }

    //INICIO SESIÓN

    //MOSTRAR FORMULARIO PARA INICIO DE SESIÓN
    @GetMapping("/iniciosesion")
    public String inicioSesion(Model model){
        model.addAttribute("usuarios", new Usuarios());
        return "login";
    }

    //BÚSQUEDA DEL USUARIO
    @GetMapping("/usuarioenviado")
    public String envioDatos(@RequestParam String nombreusuario, String password,
                             @ModelAttribute("usuarios") Usuarios usuarios,
                             Model model){

        if(usuariosService.checkExistencia(nombreusuario, password)==true){

            Usuarios usuario1=usuariosService.devolucionUsuarioCompleto(nombreusuario, password);
            model.addAttribute("usuario1", usuario1);

            return "inicioapto";
        }else{
            System.out.println("mal");
        }
        return "login";
    }
}

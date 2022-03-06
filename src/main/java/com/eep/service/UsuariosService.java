package com.eep.service;

import com.eep.entity.Usuarios;
import com.eep.service.impl.UsuariosServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UsuariosService{

    public boolean checkExistencia(String nombreUsuario, String password);
    public boolean permitirAcceso();
    public Usuarios devolucionUsuarioCompleto(String nombreusuario, String password);
    public Usuarios devolucionUsuarioCompletoNoUsernameAndPassword();
    public void cerrarSesion();
    public Usuarios addUsuario(Usuarios usuario);
    public List<Usuarios> mostrarUsuarios();
    public int borrarUsuario(int id);
}

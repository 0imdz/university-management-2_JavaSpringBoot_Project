package com.eep.service.impl;

import com.eep.entity.Usuarios;
import com.eep.repository.UsuariosJPARepository;
import com.eep.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UsuariosService")
public class UsuariosServiceImpl implements UsuariosService{

    boolean permitir;

    Usuarios usuarioLogueado=new Usuarios();

    @Autowired
    @Qualifier("usuariosJpaRepository")
    private UsuariosJPARepository usuariosRepository;

    @Override
    public Usuarios addUsuario(Usuarios usuario){
        return usuariosRepository.save(usuario);
    }

    @Override
    public boolean checkExistencia(String nombreUsuario, String password){

        boolean encontrado=false;

        if(usuariosRepository.findByNombreusuario(nombreUsuario)!=null){
            usuarioLogueado=usuariosRepository.findByNombreusuario(nombreUsuario);
            if(usuarioLogueado.getNombreusuario().equals(nombreUsuario) && usuarioLogueado.getPassword().equals(password)){
                encontrado=true;
            }
        }
        return encontrado;
    }

    @Override
    public boolean permitirAcceso(){

        permitir=false;

        if(usuariosRepository.findByNombreusuario(usuarioLogueado.getNombreusuario())!=null){
            permitir=true;
        }

        return permitir;
    }

    @Override
    public Usuarios devolucionUsuarioCompletoNoUsernameAndPassword(){

        Usuarios usuarioDevolucion=usuarioLogueado;
        return usuarioDevolucion;
    }

    @Override
    public Usuarios devolucionUsuarioCompleto(String nombreusuario, String password){

        usuarioLogueado=usuariosRepository.findByNombreusuario(nombreusuario);
        return usuarioLogueado;
    }

    @Override
    public void cerrarSesion(){

        usuarioLogueado.setNombreusuario("");
        usuarioLogueado.setPassword("");
        usuarioLogueado.setTipo("");
    }

    @Override
    public List<Usuarios> mostrarUsuarios(){
        return usuariosRepository.findAll();
    }

    @Override
    public int borrarUsuario(int id) {
        // TODO Auto-generated method stub
        usuariosRepository.deleteById(id);
        return 0;
    }
}

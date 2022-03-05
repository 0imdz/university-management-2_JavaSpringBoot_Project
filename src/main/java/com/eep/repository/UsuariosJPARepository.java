package com.eep.repository;

import com.eep.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("usuariosJpaRepository")
public interface UsuariosJPARepository extends JpaRepository <Usuarios,Serializable> {

    public Usuarios findByNombreusuario(String nombreUsuario);
    public Usuarios findByPassword(String password);
}
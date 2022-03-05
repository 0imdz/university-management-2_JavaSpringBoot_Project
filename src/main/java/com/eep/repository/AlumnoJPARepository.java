package com.eep.repository;

import com.eep.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("alumnoJpaRepository")
public interface AlumnoJPARepository extends JpaRepository <Alumno, Serializable> {

}

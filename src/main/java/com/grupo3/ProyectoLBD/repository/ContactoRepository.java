package com.grupo3.ProyectoLBD.repository;

import com.grupo3.ProyectoLBD.model.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, String> {
}
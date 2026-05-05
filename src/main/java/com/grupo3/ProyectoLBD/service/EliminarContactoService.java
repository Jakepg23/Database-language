package com.grupo3.ProyectoLBD.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class EliminarContactoService {

    @Autowired
    private JdbcTemplate jdbc;

    public void desactivarCorreo(String cedula) {
        String sql = "UPDATE FIDE_EMAIL_TB SET ESTADO = 2 WHERE CEDULA = ?";
        jdbc.update(sql, cedula);
    }

    public void desactivarTelefono(String cedula) {
        String sql = "UPDATE FIDE_TELEFONO_TB SET ESTADO = 2 WHERE CEDULA = ?";
        jdbc.update(sql, cedula);
    }
}
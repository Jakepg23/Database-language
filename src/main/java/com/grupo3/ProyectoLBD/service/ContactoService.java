package com.grupo3.ProyectoLBD.service;

import com.grupo3.ProyectoLBD.model.Contacto;
import com.grupo3.ProyectoLBD.repository.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactoService {

    @Autowired
    private ContactoRepository contactoRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // ==========================================
    // LISTAR CONTACTOS
    // ==========================================
    public List<Contacto> listarContactos() {
        return contactoRepository.findAll();
    }

    // ==========================================
    // VALIDACIONES DE DUPLICADOS
    // ==========================================
    public boolean correoExiste(String correo) {
        String sql = "SELECT COUNT(*) FROM FIDE_EMAIL_TB WHERE EMAIL = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, correo);
        return count != null && count > 0;
    }

    public boolean telefonoExiste(String telefono) {
        String sql = "SELECT COUNT(*) FROM FIDE_TELEFONO_TB WHERE NUM_TELEFONO = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, telefono);
        return count != null && count > 0;
    }

    // ==========================================
    // AGREGAR NUEVO CORREO
    // ==========================================
    public boolean agregarCorreo(String cedula, String nuevoCorreo) {

        // VALIDAR DUPLICADO
        if (correoExiste(nuevoCorreo)) {
            return false;
        }

        // INSERTAR DIRECTAMENTE EN TABLA REAL
        String sql = "INSERT INTO FIDE_EMAIL_TB (CEDULA, EMAIL) VALUES (?, ?)";
        jdbcTemplate.update(sql, cedula, nuevoCorreo);

        return true;
    }

    // ==========================================
    // AGREGAR NUEVO TELÉFONO
    // ==========================================
    public boolean agregarTelefono(String cedula, String nuevoTelefono) {

        // VALIDAR DUPLICADO
        if (telefonoExiste(nuevoTelefono)) {
            return false;
        }

        // INSERTAR DIRECTAMENTE EN TABLA REAL
        String sql = "INSERT INTO FIDE_TELEFONO_TB (CEDULA, NUM_TELEFONO) VALUES (?, ?)";
        jdbcTemplate.update(sql, cedula, nuevoTelefono);

        return true;
    }

    // ==========================================
    // ELIMINAR CORREO ESPECÍFICO
    // ==========================================
    public void eliminarCorreo(String cedula, String correo) {
        String sql = "DELETE FROM FIDE_EMAIL_TB WHERE CEDULA = ? AND EMAIL = ?";
        jdbcTemplate.update(sql, cedula, correo);
    }

    // ==========================================
    // ELIMINAR TELÉFONO ESPECÍFICO
    // ==========================================
    public void eliminarTelefono(String cedula, String telefono) {
        String sql = "DELETE FROM FIDE_TELEFONO_TB WHERE CEDULA = ? AND NUM_TELEFONO = ?";
        jdbcTemplate.update(sql, cedula, telefono);
    }
}
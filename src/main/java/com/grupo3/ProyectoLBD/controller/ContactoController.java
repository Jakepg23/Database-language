package com.grupo3.ProyectoLBD.controller;

import com.grupo3.ProyectoLBD.service.ContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contactos")
public class ContactoController {

    @Autowired
    private ContactoService contactoService;

    // ===============================
    // LISTAR CONTACTOS
    // ===============================
    @GetMapping
    public String listarContactos(
            @RequestParam(value = "error", required = false) String error,
            Model model
    ) {
        model.addAttribute("contactos", contactoService.listarContactos());
        model.addAttribute("activePage", "contactos");
        model.addAttribute("error", error);
        return "contactos/lista";
    }

    // ===============================
    // AGREGAR CORREO
    // ===============================
    @PostMapping("/agregar-correo/{cedula}")
    public String agregarCorreo(
            @PathVariable String cedula,
            @RequestParam("nuevoCorreo") String nuevoCorreo
    ) {
        boolean ok = contactoService.agregarCorreo(cedula, nuevoCorreo);

        if (!ok) {
            return "redirect:/contactos?error=correo";
        }

        return "redirect:/contactos";
    }

    // ===============================
    // AGREGAR TELEFONO
    // ===============================
    @PostMapping("/agregar-telefono/{cedula}")
    public String agregarTelefono(
            @PathVariable String cedula,
            @RequestParam("nuevoTelefono") String nuevoTelefono
    ) {
        boolean ok = contactoService.agregarTelefono(cedula, nuevoTelefono);

        if (!ok) {
            return "redirect:/contactos?error=telefono";
        }

        return "redirect:/contactos";
    }

    // ===============================
    // ELIMINAR CORREO ESPECÍFICO
    // ===============================
    @PostMapping("/eliminar-correo/{cedula}")
    public String eliminarCorreo(
            @PathVariable String cedula,
            @RequestParam("correo") String correo
    ) {
        contactoService.eliminarCorreo(cedula, correo);
        return "redirect:/contactos";
    }

    // ===============================
    // ELIMINAR TELEFONO ESPECÍFICO
    // ===============================
    @PostMapping("/eliminar-telefono/{cedula}")
    public String eliminarTelefono(
            @PathVariable String cedula,
            @RequestParam("telefono") String telefono
    ) {
        contactoService.eliminarTelefono(cedula, telefono);
        return "redirect:/contactos";
    }
}
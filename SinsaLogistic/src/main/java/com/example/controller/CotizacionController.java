package com.example.controller;

import com.example.domain.Cotizacion;
import com.example.domain.Cliente;
import com.example.service.CotizacionService;
import com.example.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

/*
Ángel Rodríguez Vargas
*/
@Controller
@RequestMapping("/cotizacion")
public class CotizacionController {

    @Autowired
    private CotizacionService cotizacionService;

    @Autowired
    private ClienteService clienteService;

    // 1. Listar cotizaciones (ADMIN)
    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("cotizaciones", cotizacionService.getCotizaciones());
        return "cotizacion/listado";
    }

    // 2. Mostrar formulario nueva cotizacion
    @GetMapping("/nuevo")
    public String nuevaCotizacion(Model model) {
        model.addAttribute("cotizacion", new Cotizacion());
        model.addAttribute("clientes", clienteService.getClientes());
        return "cotizacion/modifica";
    }

    // 3. Guardar cotizacion
    @PostMapping("/guardar")
    public String guardarCotizacion(Cotizacion cotizacion) {
        if (cotizacion.getFecha() == null) {
            cotizacion.setFecha(LocalDate.now());
        }
        cotizacionService.save(cotizacion);
        return "redirect:/cotizacion/listado";
    }

    // 4. Editar cotizacion
    @GetMapping("/modificar/{idCotizacion}")
    public String modificarCotizacion(@PathVariable Integer idCotizacion, Model model) {
        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setIdCotizacion(idCotizacion);
        cotizacion = cotizacionService.getCotizacion(cotizacion);
        model.addAttribute("cotizacion", cotizacion);
        model.addAttribute("clientes", clienteService.getClientes());
        return "cotizacion/modifica";
    }

    // 5. Eliminar cotizacion
    @GetMapping("/eliminar/{idCotizacion}")
    public String eliminarCotizacion(@PathVariable Integer idCotizacion) {
        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setIdCotizacion(idCotizacion);
        cotizacionService.delete(cotizacion);
        return "redirect:/cotizacion/listado";
    }

    // 6. Aprobar cotizacion (ADMIN)
    @GetMapping("/aprobar/{idCotizacion}")
    public String aprobarCotizacion(@PathVariable Integer idCotizacion) {
        cotizacionService.aprobar(idCotizacion);
        return "redirect:/cotizacion/listado";
    }

    // 7. Rechazar cotizacion (ADMIN)
    @GetMapping("/rechazar/{idCotizacion}")
    public String rechazarCotizacion(@PathVariable Integer idCotizacion) {
        cotizacionService.rechazar(idCotizacion);
        return "redirect:/cotizacion/listado";
    }
}
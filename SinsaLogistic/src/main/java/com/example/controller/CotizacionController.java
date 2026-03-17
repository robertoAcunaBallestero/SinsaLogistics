package com.example.controller;

import com.example.domain.Cotizacion;
import com.example.domain.Cliente;
import com.example.service.CotizacionService;
import com.example.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cotizacion")
public class CotizacionController {

    @Autowired
    private CotizacionService cotizacionService;

    @Autowired
    private ClienteService clienteService;

    // 1. Listar cotizaciones
    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("cotizaciones", cotizacionService.getCotizaciones());
        model.addAttribute("cotizacion", new Cotizacion());
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
}
package com.example.controller;

import com.example.domain.*;
import com.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

/*
Ángel Rodríguez Vargas
*/
@Controller
@RequestMapping("/catalogo")
public class CatalogoController {

    @Autowired
    private MaterialService materialService;

    @Autowired
    private CotizacionService cotizacionService;

    @Autowired
    private DetalleCotizacionService detalleCotizacionService;

    @Autowired
    private ClienteService clienteService;

    // 1. Ver catálogo de materiales (solo lectura)
    @GetMapping("/materiales")
    public String verCatalogo(Model model) {
        model.addAttribute("materiales", materialService.getMateriales());
        return "catalogo/materiales";
    }

    // 2. Mostrar formulario de nueva cotizacion
    @GetMapping("/cotizar")
    public String formCotizacion(Model model, Principal principal) {
        Cliente cliente = clienteService.getClienteByUsername(principal.getName());
        if (cliente == null) {
            model.addAttribute("error", "No tienes un perfil de cliente asociado.");
            return "catalogo/materiales";
        }
        model.addAttribute("materiales", materialService.getMateriales());
        model.addAttribute("cliente", cliente);
        return "catalogo/cotizar";
    }

    // 3. Guardar cotizacion del cliente
    @PostMapping("/guardar")
    public String guardarCotizacion(
            Principal principal,
            @RequestParam List<Integer> idMateriales,
            @RequestParam List<Integer> cantidades,
            @RequestParam String telefono,
            @RequestParam String direccionEntrega) {

        // Obtener cliente logueado automáticamente
        Cliente cliente = clienteService.getClienteByUsername(principal.getName());

        // Crear cotizacion
        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setCliente(cliente);
        cotizacion.setFecha(LocalDate.now());
        cotizacion.setEstado("PENDIENTE");
        cotizacion.setTotal(0);
        cotizacion.setTelefono(telefono);
        cotizacion.setDireccionEntrega(direccionEntrega);
        cotizacionService.save(cotizacion);

        // Procesar detalles
        int total = 0;

        for (int i = 0; i < idMateriales.size(); i++) {
            Material material = materialService.getMaterialById(idMateriales.get(i));
            int cantidad = cantidades.get(i);

            if (cantidad <= 0) continue;

            // Validar stock
            if (cantidad > material.getStock()) {
                cantidad = material.getStock();
            }

            if (cantidad <= 0) continue;

            int subtotal = material.getPrecio() * cantidad;
            total += subtotal;

            // Crear detalle
            DetalleCotizacion detalle = new DetalleCotizacion();
            detalle.setCotizacion(cotizacion);
            detalle.setMaterial(material);
            detalle.setCantidad(cantidad);
            detalle.setPrecioUnitario(material.getPrecio());
            detalle.setSubtotal(subtotal);
            detalleCotizacionService.save(detalle);

            // Descontar stock
            material.setStock(material.getStock() - cantidad);
            materialService.save(material);
        }

        // Actualizar total
        cotizacion.setTotal(total);
        cotizacionService.save(cotizacion);

        return "redirect:/catalogo/confirmacion";
    }

    // 4. Confirmacion
    @GetMapping("/confirmacion")
    public String confirmacion() {
        return "catalogo/confirmacion";
    }
}
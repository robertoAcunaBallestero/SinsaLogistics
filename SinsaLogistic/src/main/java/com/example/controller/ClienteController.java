
/**
 *
 * @Pablo Romero
 */

package com.example.controller;

import com.example.domain.Cliente;
import com.example.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // 1. Se procede a enListar clientes
    @GetMapping("/listado")
    public String listado(Model model) {
        var clientes = clienteService.getClientes();
        model.addAttribute("cliente", cliente);
        return "cliente/listado";

    }

    // 2. Se procede a mostrar formulario de nuevo cliente
    @GetMapping("/nuevo")
    public String nuevoCliente(Cliente cliente) {
        return "cliente/modifica";

    }

    //3. Se procede a guardar un cliente nuevo o editado
    @PostMapping("/guardar")
    public String guardarCliente(Cliente cliente){
        clienteService.save(cliente);
        return "redirect:/cliente/listado";
    }

    //4. Se procede a editar un cliente 
    @GetMapping("/modificar/{idCliente}")
    public String modificarCliente(Cliente cliente, Model model) {
        cliente = clienteService.getCliente(cliente);
        model.addAttribute("clliente", cliente);
        return "cliente/modifica";
    }

    // 5. Se procede a eliminar cliente
    @GetMapping("/eliminar/{idCliente}")
    public String eliminarCliente(Cliente cliente){
        clienteService.delete(cliente);
        return"redirect:/cliente/listado";
    }
    
}

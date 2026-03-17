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

    // Listar clientes
    @GetMapping("/listado")
    public String listado(Model model) {
        var clientes = clienteService.getClientes();
        model.addAttribute("clientes", clientes);
        model.addAttribute("cliente", new Cliente()); 
        return "cliente/listado";
    }

    // Mostrar formulario de nuevo cliente
    @GetMapping("/nuevo")
    public String nuevoCliente(Model model) {
        model.addAttribute("cliente", new Cliente()); 
        return "cliente/modifica";
    }

    // 3. Guardar cliente nuevo o editado
    @PostMapping("/guardar")
    public String guardarCliente(Cliente cliente) {
        clienteService.save(cliente);
        return "redirect:/cliente/listado";
    }

    // 4. Editar cliente
    @GetMapping("/modificar/{idCliente}")
    public String modificarCliente(@PathVariable Integer idCliente, Model model) {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(idCliente); 
        cliente = clienteService.getCliente(cliente);
        model.addAttribute("cliente", cliente); 
        return "cliente/modifica";
    }

    // 5. Eliminar cliente
    @GetMapping("/eliminar/{idCliente}")
    public String eliminarCliente(@PathVariable Integer idCliente) {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(idCliente); 
        clienteService.delete(cliente);
        return "redirect:/cliente/listado";
    }
}

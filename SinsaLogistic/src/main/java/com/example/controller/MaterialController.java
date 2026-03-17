package com.example.controller;
import com.example.domain.Material;
import com.example.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    // 1. Listar materiales
    @GetMapping("/listado")
    public String listado(Model model) {
        var materiales = materialService.getMateriales();
        model.addAttribute("materiales", materiales);
        model.addAttribute("material", new Material()); 
        return "material/listado";
    }

    // 2. Mostrar formulario de nuevo material
    @GetMapping("/nuevo")
    public String nuevoMaterial(Model model) {
        model.addAttribute("material", new Material()); 
        return "material/modifica";
    }

    // 3. Guardar material
    @PostMapping("/guardar")
    public String guardarMaterial(Material material) {
        materialService.save(material);
        return "redirect:/material/listado";
    }

    // 4. Editar material
    @GetMapping("/modificar/{idMaterial}")
    public String modificarMaterial(@PathVariable Integer idMaterial, Model model) {
        Material material = new Material();
        material.setIdMaterial(idMaterial); 
        material = materialService.getMaterial(material);
        model.addAttribute("material", material);
        return "material/modifica";
    }

    // 5. Eliminar material
    @GetMapping("/eliminar/{idMaterial}")
    public String eliminarMaterial(@PathVariable Integer idMaterial) {
        Material material = new Material();
        material.setIdMaterial(idMaterial); 
        materialService.delete(material);
        return "redirect:/material/listado";
    }
}

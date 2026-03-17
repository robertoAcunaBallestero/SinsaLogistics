/**
 *
 * @Pablo Romero
 */


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

    // 1. Se proceden a enlsitar los materiales 

    @GetMapping("/listado")
    public String listado(Model model){
        var materiales = materialService.getMateriales();
        model.addAttribute("materiales", materiales);
        return "material/listado";

    }

    // 2. Se procede a mostrar el formulario del nuevo material
    @GetMapping("/nuevo")
    public String nuevoMaterial(Material material) {
        return "material/modifica";
    }

    // 3. Se procede a guardar el material 

    @PostMapping("/guardar")
    public String guardarMaterial(Material material) {
        materialService.save(material);
        return "redirect:/material/listado";

    }

    // 4. Se procede a editar el material

    @GetMapping("/modificar/{idMaterial}")
    public String modificarMaterial(Material material, Model model){
        material = materialService.getMaterial(material);
        model.addAttribute("material", material);
        return "material/modifica";
    }

    //5. Se procede a eliminar el material 

    @GetMapping("/eliminar/{idMaterial}")
    public String eliminarMaterial(Material material){
        materialService.delete(material);
        return "redirect:/material/listado";
    }
    
}

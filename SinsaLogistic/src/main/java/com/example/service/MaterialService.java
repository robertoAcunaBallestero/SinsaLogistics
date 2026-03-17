
package com.example.service;

/**
 *
 * @author Administrador(a)
 */

import com.example.domain.Material;
import java.util.List;

public interface MaterialService {

    public List<Material> getMateriales();

    public Material getMaterial(Material material);

    public void save(Material material);

    public void delete(Material material);

    public List<Material> getMaterialesStockBajo(Integer stock);
}

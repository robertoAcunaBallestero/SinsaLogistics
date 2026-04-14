package com.example.service;

import com.example.domain.Material;
import com.example.repository.MaterialRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public List<Material> getMateriales() {
        return materialRepository.findAll();
    }

    @Override
    public Material getMaterial(Material material) {
        return materialRepository.findById(material.getIdMaterial()).orElse(null);
    }

    @Override
    public Material getMaterialById(Integer id) {  
        return materialRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Material material) {
        materialRepository.save(material);
    }

    @Override
    public void delete(Material material) {
        materialRepository.delete(material);
    }

    @Override
    public List<Material> getMaterialesStockBajo(Integer stock) {
        return materialRepository.findByStockLessThanEqual(stock);
    }
}

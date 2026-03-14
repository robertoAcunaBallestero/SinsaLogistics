/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.service;

/**
 *
 * @author Administrador(a)
 */

import com.sinsa.domain.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {

    //Cumplimos la Historia de Usuario de "Alertas de Stock"
    //En esta consulta te devuelve todos los materiales que tengan el stock igual o menor a un número.
    @Query("SELECT m FROM Material m WHERE m.stock <= :limiteMinimo")
    List<Material> buscarMaterialesConBajoStock(@Param("limiteMinimo") Integer limiteMinimo);
}

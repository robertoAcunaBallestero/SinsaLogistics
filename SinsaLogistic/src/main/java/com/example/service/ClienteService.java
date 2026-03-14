/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.service;

import com.sinsa.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrador(a)
 */


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
    //Usamos Spring para crea la consulta automáticamente por el nombre del método
    //Se usa el Útil para cuando un cliente quiera iniciar sesión o buscar sus datos
    Cliente findByCorreo(String correo);
}

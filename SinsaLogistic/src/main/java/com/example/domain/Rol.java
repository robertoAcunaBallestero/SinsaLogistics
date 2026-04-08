package com.example.domain;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 *
 * Ángel Rodríguez
 */

@Entity
@Table(name = "rol")
public class Rol implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRol;

    private String nombre; // ADMIN, CLIENTE

    public Rol() {}

    public Integer getIdRol() { return idRol; }
    public void setIdRol(Integer idRol) { this.idRol = idRol; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}

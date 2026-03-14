/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.domain;

import jakarta.persistence.*;

/**
 *
 * @author Administrador(a)
 */

@Entity
@Table(name = "Material")
public class Material {

    //Creamos la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_material")
    private Integer idMaterial;

    //Definimos los atributos de la tabla
    private String nombre;
    private Integer precio;
    private Integer stock;
    private String descripcion;

    //Definimos la relación (Lo dejé así por si no se ha definido)
    /*
    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;
    */

    //Creamos el constructor 
    public Material() {
    }

    //Creamos los Getters & Setters
    public Integer getIdMaterial() { return idMaterial; }
    public void setIdMaterial(Integer idMaterial) { this.idMaterial = idMaterial; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public Integer getPrecio() { return precio; }
    public void setPrecio(Integer precio) { this.precio = precio; }
    
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}

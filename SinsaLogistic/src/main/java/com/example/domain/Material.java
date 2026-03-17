package com.example.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

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

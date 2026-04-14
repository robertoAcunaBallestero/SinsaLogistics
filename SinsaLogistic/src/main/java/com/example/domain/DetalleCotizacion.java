package com.example.domain;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "detalle_cotizacion")
public class DetalleCotizacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Integer idDetalle;

    @ManyToOne
    @JoinColumn(name = "id_cotizacion")
    private Cotizacion cotizacion;

    @ManyToOne
    @JoinColumn(name = "id_material")
    private Material material;

    private Integer cantidad;

    @Column(name = "precio_unitario")
    private Integer precioUnitario;

    private Integer subtotal;

    public DetalleCotizacion() {}

    public Integer getIdDetalle() { return idDetalle; }
    public void setIdDetalle(Integer idDetalle) { this.idDetalle = idDetalle; }

    public Cotizacion getCotizacion() { return cotizacion; }
    public void setCotizacion(Cotizacion cotizacion) { this.cotizacion = cotizacion; }

    public Material getMaterial() { return material; }
    public void setMaterial(Material material) { this.material = material; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public Integer getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(Integer precioUnitario) { this.precioUnitario = precioUnitario; }

    public Integer getSubtotal() { return subtotal; }
    public void setSubtotal(Integer subtotal) { this.subtotal = subtotal; }
}
package com.example.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Cotizacion")
public class Cotizacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cotizacion")
    private Integer idCotizacion;

    private LocalDate fecha;
    private String estado;
    private Integer total;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "direccion_entrega")
    private String direccionEntrega;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "cotizacion", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DetalleCotizacion> detalles;

    public Cotizacion() {}

    public Integer getIdCotizacion() { return idCotizacion; }
    public void setIdCotizacion(Integer idCotizacion) { this.idCotizacion = idCotizacion; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Integer getTotal() { return total; }
    public void setTotal(Integer total) { this.total = total; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccionEntrega() { return direccionEntrega; }
    public void setDireccionEntrega(String direccionEntrega) { this.direccionEntrega = direccionEntrega; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public List<DetalleCotizacion> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleCotizacion> detalles) { this.detalles = detalles; }
}
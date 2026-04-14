package com.example.service;

import com.example.domain.Cotizacion;
import java.util.List;


public interface CotizacionService {
    List<Cotizacion> getCotizaciones();
    Cotizacion getCotizacion(Cotizacion cotizacion);
    void save(Cotizacion cotizacion);
    void delete(Cotizacion cotizacion);
    void aprobar(Integer idCotizacion);   
    void rechazar(Integer idCotizacion);  
}

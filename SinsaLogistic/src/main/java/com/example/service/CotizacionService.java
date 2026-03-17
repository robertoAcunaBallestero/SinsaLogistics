package com.example.service;

import com.example.domain.Cotizacion;
import java.util.List;

public interface CotizacionService {
    public List<Cotizacion> getCotizaciones();
    public Cotizacion getCotizacion(Cotizacion cotizacion);
    public void save(Cotizacion cotizacion);
    public void delete(Cotizacion cotizacion);
}

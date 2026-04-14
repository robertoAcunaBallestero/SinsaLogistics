package com.example.service;

import com.example.domain.DetalleCotizacion;
import java.util.List;

public interface DetalleCotizacionService {
    List<DetalleCotizacion> getDetalles();
    void save(DetalleCotizacion detalle);
    void delete(DetalleCotizacion detalle);
}

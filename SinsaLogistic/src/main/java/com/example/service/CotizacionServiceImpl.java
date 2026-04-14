package com.example.service;

import com.example.domain.Cotizacion;
import com.example.domain.DetalleCotizacion;
import com.example.repository.CotizacionRepository;
import com.example.repository.DetalleCotizacionRepository;
import com.example.service.MaterialService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CotizacionServiceImpl implements CotizacionService {

    @Autowired
    private CotizacionRepository cotizacionRepository;

    @Autowired
    private DetalleCotizacionRepository detalleCotizacionRepository;

    @Autowired
    private MaterialService materialService;

    @Override
    public List<Cotizacion> getCotizaciones() {
        return cotizacionRepository.findAll();
    }

    @Override
    public Cotizacion getCotizacion(Cotizacion cotizacion) {
        return cotizacionRepository.findById(cotizacion.getIdCotizacion()).orElse(null);
    }

    @Override
    public void save(Cotizacion cotizacion) {
        cotizacionRepository.save(cotizacion);
    }

    @Override
    public void delete(Cotizacion cotizacion) {
        Cotizacion c = cotizacionRepository.findById(cotizacion.getIdCotizacion()).orElse(null);
        if (c != null) {
            cotizacionRepository.delete(c);
        }
    }

    @Override
    public void aprobar(Integer idCotizacion) {
        Cotizacion cotizacion = cotizacionRepository.findById(idCotizacion).orElse(null);
        if (cotizacion != null) {
            cotizacion.setEstado("APROBADA");
            cotizacionRepository.save(cotizacion);
        }
    }

    @Override
    public void rechazar(Integer idCotizacion) {
        Cotizacion cotizacion = cotizacionRepository.findById(idCotizacion).orElse(null);
        if (cotizacion != null) {
            // Devolver stock de cada material
            if (cotizacion.getDetalles() != null) {
                for (DetalleCotizacion detalle : cotizacion.getDetalles()) {
                    var material = detalle.getMaterial();
                    material.setStock(material.getStock() + detalle.getCantidad());
                    materialService.save(material);
                }
            }
            cotizacion.setEstado("RECHAZADA");
            cotizacionRepository.save(cotizacion);
        }
    }
}
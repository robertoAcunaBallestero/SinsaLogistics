package com.example.service;

import com.example.domain.Cotizacion;
import com.example.repository.CotizacionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CotizacionServiceImpl implements CotizacionService {

    @Autowired
    private CotizacionRepository cotizacionRepository;

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
        cotizacionRepository.delete(cotizacion);
    }
}

package com.example.service;

import com.example.domain.DetalleCotizacion;
import com.example.repository.DetalleCotizacionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleCotizacionServiceImpl implements DetalleCotizacionService {

    @Autowired
    private DetalleCotizacionRepository detalleCotizacionRepository;

    @Override
    public List<DetalleCotizacion> getDetalles() {
        return detalleCotizacionRepository.findAll();
    }

    @Override
    public void save(DetalleCotizacion detalle) {
        detalleCotizacionRepository.save(detalle);
    }

    @Override
    public void delete(DetalleCotizacion detalle) {
        detalleCotizacionRepository.delete(detalle);
    }
}

package com.milenyumsoft.venta_service.service;

import com.milenyumsoft.venta_service.modelo.Venta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService implements IVentaService{
    @Override
    public String crearVenta(Venta venta) {

        return "";
    }

    @Override
    public List<Venta> getListVent() {
        return List.of();
    }

    @Override
    public Venta getVentById(Long idVenta) {
        return null;
    }

    @Override
    public String deleteVenta(Long idVenta) {
        return "";
    }

    @Override
    public String payVenta(Long idVenta, Long pay) {
        return "";
    }
}

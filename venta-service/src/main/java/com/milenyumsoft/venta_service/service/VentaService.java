package com.milenyumsoft.venta_service.service;

import com.milenyumsoft.venta_service.modelo.Venta;
import com.milenyumsoft.venta_service.repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService implements IVentaService{


    @Autowired
    private IVentaRepository ventaRepo;


    @Override
    public String crearVenta(Venta venta) {



        return "";
    }

    @Override
    public List<Venta> getListVent() {

        return ventaRepo.findAll();
    }

    @Override
    public Venta getVentById(Long idVenta) {
        return ventaRepo.findById(idVenta).orElse(null);
    }

    @Override
    public String deleteVenta(Long idVenta) {

        ventaRepo.deleteById(idVenta);
        return "Venta Eliminada.";
    }

    @Override
    public String payVenta(Long idVenta, Long pay) {
        return "";
    }
}

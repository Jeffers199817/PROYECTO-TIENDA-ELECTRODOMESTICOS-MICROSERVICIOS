package com.milenyumsoft.venta_service.service;

import com.milenyumsoft.venta_service.dto.VentaDTO;
import com.milenyumsoft.venta_service.modelo.Venta;
import com.milenyumsoft.venta_service.repository.ICarritoCompraDTORepository;
import com.milenyumsoft.venta_service.repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class VentaService implements IVentaService{


    @Autowired
    private IVentaRepository ventaRepo;

    @Autowired
    private ICarritoCompraDTORepository carritoRepo;


    @Override
    public String crearVenta(Venta venta) {

       Venta vent = venta;
       Long numeroDeIdentificacion;

       Random random = new Random();

       numeroDeIdentificacion = random.nextLong(900000000) + 100000000;

       vent.setNumeroIdentificacion(numeroDeIdentificacion);

            ventaRepo.save(vent);

        return "Venta creada, Exitosamente.";
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
    public String deleteVenta(Long idcarrito) {

    Venta venta = ventaRepo.traerDatoVenta(idcarrito);
        System.out.println("NOmbre" + venta.getIdVenta());

    Long idVenta =venta.getIdVenta();

    ventaRepo.deleteById(idVenta);
        return "Venta Eliminada.";
    }

    @Override
    public String payVenta(Long idVenta, Long pay) {

        // Lógica para procesar el pago
        Venta venta = ventaRepo.findById(idVenta)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        // Marcar la venta como pagada
        if(pay == 1) {
            venta.setVentaRealizadoPagado(true);
            ventaRepo.save(venta);
        }else{
            return "No se pudo pagar, reintente nuevamente";
        }

        return "Pago realizado exitosamente para la venta con ID: " + idVenta;

    }

    @Override
    public VentaDTO traerVentaDTO(Long idventa) {

        Venta vent = this.getVentById(idventa);


        VentaDTO ventaDTO = new VentaDTO();

        ventaDTO.setIdVenta(vent.getIdVenta());
        ventaDTO.setNumeroIdentificacion(vent.getNumeroIdentificacion());
        ventaDTO.setFechaVenta(vent.getFechaVenta());
        ventaDTO.setVentaRealizadoPagado(vent.isVentaRealizadoPagado());
        ventaDTO.setCarritoCompraDTO(carritoRepo.traerCarrito(vent.getIdCarritoCompra()));


        return ventaDTO;
    }
}

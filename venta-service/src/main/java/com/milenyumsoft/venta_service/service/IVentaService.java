package com.milenyumsoft.venta_service.service;

import com.milenyumsoft.venta_service.dto.VentaDTO;
import com.milenyumsoft.venta_service.modelo.Venta;

import java.util.List;

public interface IVentaService {

    //1.- Metodos CRUD

    public String crearVenta(Venta venta);

    public List<Venta> getListVent();

    public Venta getVentById(Long idVenta);

    public String deleteVenta(Long idCarrito);

    public String payVenta(Long idVenta, Long pay);


    public VentaDTO traerVentaDTO(Long idventa);

    public Venta traerVenta(Long idventa);
}

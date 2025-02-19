package com.milenyumsoft.carrito_compras_service.service;
import com.milenyumsoft.carrito_compras_service.dto.CarritoCompraDTO;
import com.milenyumsoft.carrito_compras_service.modelo.CarritoCompra;

import java.util.List;

public interface ICarritoCompraService {
    public String crearCarritoCompra(CarritoCompra carritoCompra);
    public String eliminarCarritoCompra(Long idCarritoCompra);

    public CarritoCompraDTO traerCarrito(Long idCarritoCompra);
    public List<CarritoCompra> traerListaCarriot();

    public String anadirCarritoCompraProducto(Long idCarritoCompra, Long idProducto);

    public  String eliminarCarritoCompraProducto(Long idCarritoCompra, Long idProducto);

}


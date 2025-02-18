package com.milenyumsoft.carrito_compras_service.service;

import com.milenyumsoft.carrito_compras_service.modelo.CarritoCompra;
import com.milenyumsoft.carrito_compras_service.modelo.Producto;

public interface ICarritoCompraService {
    public String crearCarritoCompra(CarritoCompra carritoCompra);
    public String eliminarCarritoCompra(Long idCarritoCompra);

    public String anadirCarritoCompraProducto(Long idCarritoCompra, Long idProducto);

    public  String eliminarCarritoCompraProducto(Long idCarritoCompra, Long idProducto);

}


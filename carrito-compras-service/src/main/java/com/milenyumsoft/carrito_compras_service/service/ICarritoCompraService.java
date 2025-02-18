package com.milenyumsoft.carrito_compras_service.service;

import com.milenyumsoft.carrito_compras_service.modelo.CarritoCompra;
import com.milenyumsoft.carrito_compras_service.modelo.Producto;

public interface ICarritoCompraService {

public CarritoCompra anadirCarritoCompra(Long idCarritoCompra, Long idProducto);

public CarritoCompra eliminarCarritoCompra(Long idCarritoCompra, Long idProducto);

}


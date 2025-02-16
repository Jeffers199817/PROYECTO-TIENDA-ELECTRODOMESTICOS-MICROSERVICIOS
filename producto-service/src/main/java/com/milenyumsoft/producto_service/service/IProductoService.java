package com.milenyumsoft.producto_service.service;

import com.milenyumsoft.producto_service.modelo.Producto;

import java.util.List;

public interface IProductoService {


    public List<Producto> findAll();

    public Producto getProductoById(Long idProducto);

    public Producto editProductoById(Long idProducto);

    public String crearProducto(Producto producto);

    public String eliminarProducto(Long idProducto);

}

package com.milenyumsoft.carrito_compras_service.controller;


import com.milenyumsoft.carrito_compras_service.dto.CarritoCompraDTO;
import com.milenyumsoft.carrito_compras_service.modelo.CarritoCompra;
import com.milenyumsoft.carrito_compras_service.service.CarritoCompraService;
import com.milenyumsoft.carrito_compras_service.service.ICarritoCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("carrito/compra")
public class CarritoCompraController {

    @Autowired
    private ICarritoCompraService carritoSV;


    @PostMapping("crear")
    public String crearCarritoCompra(@RequestBody CarritoCompra carritoCompra){
        return  carritoSV.crearCarritoCompra(carritoCompra);
    }

    //Endpoint para traer carrito de compra
    @GetMapping("traer/{idCarrito}")
    public CarritoCompraDTO traerCarrito(@PathVariable("idCarrito") Long idCarrito){
        return carritoSV.traerCarrito(idCarrito);
    }

    //Endpoint para traer todos los carritos
    @GetMapping("traer/todo")
    public List<CarritoCompra> traerListaCarrito(){
        return carritoSV.traerListaCarriot();
    }

    // Endpoint para eliminar un carrito de compras
    @DeleteMapping("eliminar/{idCarritoCompra}")
    public String eliminarCarritoCompra(@PathVariable Long idCarritoCompra) {
        return carritoSV.eliminarCarritoCompra(idCarritoCompra);
    }

    // Endpoint para añadir un producto a un carrito de compras
    @PostMapping("anadir-producto/{idCarritoCompra}/{idProducto}")
    public String anadirCarritoCompraProducto(
            @PathVariable Long idCarritoCompra,
            @PathVariable Long idProducto) {
        return carritoSV.anadirCarritoCompraProducto(idCarritoCompra, idProducto);
    }

    // Endpoint para eliminar un producto de un carrito de compras
    @DeleteMapping("eliminar-producto/{idCarritoCompra}/{idProducto}")
    public String eliminarCarritoCompraProducto(
            @PathVariable Long idCarritoCompra,
            @PathVariable Long idProducto) {
        return carritoSV.eliminarCarritoCompraProducto(idCarritoCompra, idProducto);
    }




}

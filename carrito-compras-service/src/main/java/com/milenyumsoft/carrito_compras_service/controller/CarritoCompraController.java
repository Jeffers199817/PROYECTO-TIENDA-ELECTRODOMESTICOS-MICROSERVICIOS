package com.milenyumsoft.carrito_compras_service.controller;


import com.milenyumsoft.carrito_compras_service.modelo.CarritoCompra;
import com.milenyumsoft.carrito_compras_service.service.ICarritoCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("carrito/compra")
public class CarritoCompraController {

    @Autowired
    private ICarritoCompraService carritoSV;


    @PostMapping("crear")
    public String crearCarritoCompra(@RequestBody CarritoCompra carritoCompra){


        return  carritoSV.crearCarritoCompra(carritoCompra);
    }





}

package com.milenyumsoft.venta_service.controller;

import com.milenyumsoft.venta_service.modelo.Venta;
import com.milenyumsoft.venta_service.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/venta")
public class VentaController {

    @Autowired
    private IVentaService ventaSV;

    @GetMapping("/crear")
    public String crearVenta(@RequestBody  Venta venta){
        return  ventaSV.crearVenta(venta);
    }




}

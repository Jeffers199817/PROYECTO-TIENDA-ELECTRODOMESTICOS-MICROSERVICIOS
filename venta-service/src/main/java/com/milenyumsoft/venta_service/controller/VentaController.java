package com.milenyumsoft.venta_service.controller;

import com.milenyumsoft.venta_service.dto.VentaDTO;
import com.milenyumsoft.venta_service.modelo.Venta;
import com.milenyumsoft.venta_service.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("venta")
public class VentaController {

    @Autowired
    private IVentaService ventaSV;

    @PostMapping("/crear")
    public String crearVenta(@RequestBody  Venta venta){
        return  ventaSV.crearVenta(venta);
    }

    @GetMapping("/traer/todo")
    public List<Venta> traerListaVenta(){
        return ventaSV.getListVent();
    }


    @GetMapping("/traer/{idventa}")
    public VentaDTO traerVentaDTO(@PathVariable("idventa")Long idventa  ){

        return ventaSV.traerVentaDTO(idventa);
    }


    @DeleteMapping("/delete/{idcarrito}")
    public String eliminarVenta(@PathVariable("idcarrito") Long idcarrito)
    {
        return ventaSV.deleteVenta(idcarrito);
    }




}

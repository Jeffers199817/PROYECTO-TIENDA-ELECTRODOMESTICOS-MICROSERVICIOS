package com.milenyumsoft.producto_service.controller;

import com.milenyumsoft.producto_service.ProductoServiceApplication;
import com.milenyumsoft.producto_service.modelo.Producto;
import com.milenyumsoft.producto_service.service.IProductoService;
import com.milenyumsoft.producto_service.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {


    @Autowired
    private IProductoService productoService;

    @Value("${server.port}")
    private int serverPort;



    @PostMapping("/crear")
    public String crearProducto(@Valid @RequestBody Producto producto){

       return  productoService.crearProducto(producto);

    }

    @GetMapping("/traer/{idProducto}")
    public Producto getProductoById(@PathVariable("idProducto") Long idProducto){


        System.out.println("Estoy comunicandome por el puerto: " + serverPort);

        return productoService.getProductoById(idProducto);
    }

    @GetMapping("/traer/todo")
    public List<Producto> getAllProducto(){

        return productoService.findAll();
    }

    @DeleteMapping("/eliminar/{idProducto}")
    public String eliminarProducto(@PathVariable("idProducto") Long idProducto){

        return productoService.eliminarProducto(idProducto);
    }

    @PutMapping("/editar")
    public String editarProducto(@Valid @RequestBody Producto producto){

        return productoService.editProducto(producto);
    }
}

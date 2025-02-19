package com.milenyumsoft.carrito_compras_service.repository;

import com.milenyumsoft.carrito_compras_service.dto.ProductoDTO;
import com.milenyumsoft.carrito_compras_service.modelo.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;


@Repository
@FeignClient( name= "producto-service")
public interface IProductoRepository {


    @GetMapping("/traer/{idProducto}")
    public Optional<Producto> traerProducto(@PathVariable("idProducto") Long idProducto);


}

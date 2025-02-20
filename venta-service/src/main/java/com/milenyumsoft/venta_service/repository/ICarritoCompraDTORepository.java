package com.milenyumsoft.venta_service.repository;

import com.milenyumsoft.venta_service.dto.CarritoCompraDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
@FeignClient(name="carrito-compras-service" , url= "localhost:8085")
public interface ICarritoCompraDTORepository {

    @GetMapping("/carrito/compra/traer/{idCarrito}")
    public CarritoCompraDTO traerCarrito(@PathVariable("idCarrito") Long idCarrito);


}

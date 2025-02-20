package com.milenyumsoft.carrito_compras_service.repository;

import com.milenyumsoft.carrito_compras_service.dto.VentaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Repository
@FeignClient(name="venta-service" , url="localhost:8090")
public interface IVentaRepository {

    @PostMapping("/venta/crear")
    public String crearVenta(VentaDTO ventaDTO);


    @DeleteMapping("/venta/delete")
    public String deleteVenta(Long id);
}

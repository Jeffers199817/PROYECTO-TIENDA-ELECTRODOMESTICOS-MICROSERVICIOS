package com.milenyumsoft.carrito_compras_service.repository;

import com.milenyumsoft.carrito_compras_service.modelo.CarritoCompra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICarritoCompraRepository extends JpaRepository<CarritoCompra,Long> {


}

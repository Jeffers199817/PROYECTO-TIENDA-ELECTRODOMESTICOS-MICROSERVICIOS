package com.milenyumsoft.carrito_compras_service.repository;

import com.milenyumsoft.carrito_compras_service.modelo.CarritoCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface ICarritoCompraRepository extends JpaRepository<CarritoCompra,Long> {

    @Query("SELECT c FROM CarritoCompra c WHERE c.idCarritoCompra = :idCarritoCompra")
    public CarritoCompra buscarCarritoCompraById(@Param("idCarritoCompra") Long idCarritoCompra);

}

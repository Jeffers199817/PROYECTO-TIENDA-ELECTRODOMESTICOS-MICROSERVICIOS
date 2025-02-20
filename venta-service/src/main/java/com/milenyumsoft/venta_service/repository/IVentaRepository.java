package com.milenyumsoft.venta_service.repository;

import com.milenyumsoft.venta_service.modelo.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IVentaRepository extends JpaRepository<Venta,Long> {


    @Query("SELECT v FROM Venta v WHERE v.idCarritoCompra = :idCarritoCompra")
    public Venta traerDatoVenta(@Param("idCarritoCompra") Long idCarritoCompra);
}

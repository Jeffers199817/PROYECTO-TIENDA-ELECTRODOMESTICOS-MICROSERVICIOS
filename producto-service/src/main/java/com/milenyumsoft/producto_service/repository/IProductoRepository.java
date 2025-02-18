package com.milenyumsoft.producto_service.repository;

import com.milenyumsoft.producto_service.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {


    @Query("SELECT p FROM Producto p WHERE p.codigoProducto = :codigoProducto OR LOWER(p.nombreProducto) = LOWER(:nombreProducto)")
    List<Producto> traerDatosNombreCodigoProducto(@Param("codigoProducto") Long codigoProducto, @Param("nombreProducto") String nombreProducto);

    @Query("SELECT p FROM Producto p WHERE p.codigoProducto = :codigoProducto ")
     Optional<Producto> traerDatosCodigoProducto(@Param("codigoProducto") Long codigoProducto);


    @Query("SELECT p FROM Producto p WHERE LOWER(p.nombreProducto) = LOWER(:nombreProducto)")
     Optional<Producto> traerDatosNombreProducto( @Param("nombreProducto") String nombreProducto);
}

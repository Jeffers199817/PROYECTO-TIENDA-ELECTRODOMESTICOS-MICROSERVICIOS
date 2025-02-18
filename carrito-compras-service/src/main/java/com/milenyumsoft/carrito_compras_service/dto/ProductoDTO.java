package com.milenyumsoft.carrito_compras_service.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class ProductoDTO {

    private Long idProducto;
    private Long codigoProducto;
    private String nombreProducto;
    private String marcaProducto;
    private double precioUnitarioProducto;

}

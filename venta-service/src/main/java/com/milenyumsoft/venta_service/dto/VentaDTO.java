package com.milenyumsoft.venta_service.dto;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VentaDTO {


    private Long idVenta;
    private Long numeroIdentificacion;
    private Date fechaVenta;
    private boolean ventaRealizadoPagado;
    private CarritoCompraDTO carritoCompraDTO;


}

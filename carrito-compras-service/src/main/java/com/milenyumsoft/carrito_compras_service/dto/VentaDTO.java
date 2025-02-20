package com.milenyumsoft.carrito_compras_service.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
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

}

package com.milenyumsoft.venta_service.dto;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarritoCompraDTO {

    private Long idCarritoCompra;
    private double sumaTotal = 0.0;
    private List<Long> listaIdProductos = new ArrayList<>();
    private boolean ventaRealizadaPagado;
}

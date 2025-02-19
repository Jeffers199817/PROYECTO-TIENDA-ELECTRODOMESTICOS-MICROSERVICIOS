package com.milenyumsoft.carrito_compras_service.dto;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarritoCompraDTO {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarritoCompra;
    private double sumaTotal = 0.0;
    //Lista de IDS de productos
    private List<ProductoDTO> listaProductosDTO = new ArrayList<>();


}

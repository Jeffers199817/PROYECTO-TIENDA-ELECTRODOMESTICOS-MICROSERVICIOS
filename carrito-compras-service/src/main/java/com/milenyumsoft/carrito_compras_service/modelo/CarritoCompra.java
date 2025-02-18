package com.milenyumsoft.carrito_compras_service.modelo;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarritoCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarritoCompra;
    private double sumaTotal = 0.0;
    @ManyToMany
    private List<Producto> listaProductos = new ArrayList<>();


}

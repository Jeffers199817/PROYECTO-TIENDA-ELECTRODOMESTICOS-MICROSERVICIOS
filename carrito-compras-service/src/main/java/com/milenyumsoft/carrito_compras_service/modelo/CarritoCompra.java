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
    //Lista de IDS de productos
    @ElementCollection
    @CollectionTable(name="carrito_productos", joinColumns = @JoinColumn(name="carrito_id"))
    @Column(name="producto_id")
    private List<Long> listaIdProductos = new ArrayList<>();
    private boolean ventaRealizadaPagado;


}

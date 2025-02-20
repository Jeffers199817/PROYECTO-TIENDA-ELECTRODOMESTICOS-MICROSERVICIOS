package com.milenyumsoft.venta_service.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;
    private Long numeroIdentificacion;
    private Date fechaVenta;
    private boolean ventaRealizadoPagado;


    @PrePersist
    public void prePersist(){
        this.fechaVenta = new Date();
    }
}

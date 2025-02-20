package com.milenyumsoft.venta_service.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Setter @Getter
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
    private Long idCarritoCompra;


    @PrePersist
    public void prePersist(){
        ZoneId zonaHorariaEcuador = ZoneId.of("America/Guayaquil");
        ZonedDateTime fechaHoraEcuador = ZonedDateTime.now(zonaHorariaEcuador);
        this.fechaVenta = Date.from(fechaHoraEcuador.toInstant());
    }
}

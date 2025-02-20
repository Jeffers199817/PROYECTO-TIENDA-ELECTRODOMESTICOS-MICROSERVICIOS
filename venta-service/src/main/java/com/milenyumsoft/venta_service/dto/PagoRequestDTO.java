package com.milenyumsoft.venta_service.dto;

import lombok.*;

@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class PagoRequestDTO {
    private Long idVenta;
    private Long pay;

}

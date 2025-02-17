package com.milenyumsoft.producto_service.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idProducto;


    @NotNull(message ="El código del producto no puede ser nulo")


    @Digits(integer = 10, fraction = 0, message = "El código del producto debe ser un número entero")
    private Long codigoProducto;

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "El nombre del producto solo puede contener letras y números")
    private String nombreProducto;

    @NotBlank(message = "La marca del producto no puede estar vacía")

    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "La marca del producto solo puede contener letras y números")
    private String marcaProducto;

    @NotNull(message = "El precio unitario no puede ser nulo")

    @Positive(message = "El precio unitario debe ser un número positivo")
    private double precioUnitarioProducto;

}

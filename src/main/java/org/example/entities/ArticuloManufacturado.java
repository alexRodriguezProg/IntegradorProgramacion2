package org.example.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

public class ArticuloManufacturado extends Articulo {
    private String descripcion;
    private String preparacion;
    private Integer tiempoEstimadoEnMinutos;

    private Set<ArticuloManufacturadoDetalle> detalles = new HashSet<>();

    public ArticuloManufacturado(String denominacion, Double precioVenta, String descripcion, int tiempoEstimadoEnMinutos, String preparacion) {
        super(denominacion, precioVenta);
        this.descripcion = descripcion;
        this.tiempoEstimadoEnMinutos = tiempoEstimadoEnMinutos;
        this.preparacion = preparacion;
    }
}

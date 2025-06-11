package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public abstract class Articulo extends Base {
    protected String denominacion;
    protected Double precioVenta;

    private Set<Imagen> imagenes = new HashSet<>();
    private UnidadMedida unidadMedida = new UnidadMedida();

    public Articulo(String denominacion, Double precioVenta) {
        super();
    }
}

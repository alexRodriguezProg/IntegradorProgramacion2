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

public class Empresa extends Base {
    private String nombre;
    private String razonSocial;
    private Integer cuit;

    private Set<Sucursal> sucursales = new HashSet<>();

}

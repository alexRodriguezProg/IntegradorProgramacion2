package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Domicilio extends Base {
    private String calle;
    private Integer numero;
    private Integer cp;

    private Localidad localidad = new Localidad();
}

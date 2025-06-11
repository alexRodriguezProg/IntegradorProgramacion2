package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ArticuloManufacturadoDetalle extends Base {
    private Integer cantidad;

    private ArticuloInsumo insumo = new ArticuloInsumo();
}


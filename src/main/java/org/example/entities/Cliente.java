package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Cliente extends Base {
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private LocalDate fechaNacimiento;

    private Imagen imagen = new Imagen();
    private Usuario usuario = new Usuario();
    private Set<Domicilio> domicilios = new HashSet<>();
    private Set<Pedido> pedidos = new HashSet<>();
}

package org.example.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import org.example.enums.TipoEnvio;
import org.example.enums.Estado;
import org.example.enums.FormaPago;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Pedido extends Base {
    private Estado estado;
    private LocalTime horaEstimadaFinalizacion;
    private Double total;
    private Double totalCosto;
    private TipoEnvio tipoEnvio;
    private FormaPago formaPago;
    private LocalDate fechaPedido;

    private Factura factura = new Factura();
    private Set<DetallePedido> detallePedido = new HashSet<>();
    private Sucursal sucursal = new Sucursal();
    private Domicilio domicilio = new Domicilio();

    public Pedido(LocalDate fechaPedido, FormaPago formaPago, TipoEnvio tipoEnvio) {
        this.fechaPedido = fechaPedido;
        this.formaPago = formaPago;
        this.tipoEnvio = tipoEnvio;
        this.estado = Estado.PENDIENTE;
        this.horaEstimadaFinalizacion = LocalTime.now().plusMinutes(30); // simulación
    }

    public void agregarDetalle(DetallePedido detalle) {
        this.detallePedido.add(detalle);
        total = detalle.getSubTotal();
    }
}

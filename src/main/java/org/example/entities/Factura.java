package org.example.entities;

import java.time.LocalDate;
import org.example.enums.FormaPago;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Factura extends Base {
    private LocalDate fechaFacturacion;
    private Integer mpPaymentId;
    private Integer mpMerchantOrderId;
    private String mpPreferenceId;
    private String mpPaymentType;
    private FormaPago formaPago;
    private Double totalVenta;

    public Factura(LocalDate fechaFacturacion, FormaPago formaPago, Double totalVenta) {
        this.fechaFacturacion = fechaFacturacion;
        this.formaPago = formaPago;
        this.totalVenta = totalVenta;
        this.mpPaymentId = 1234;
        this.mpMerchantOrderId = 5678;
        this.mpPreferenceId = "MP-XYZ-001";
        this.mpPaymentType = "credit_card";
    }
}

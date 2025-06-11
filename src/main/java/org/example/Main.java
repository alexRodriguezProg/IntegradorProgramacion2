package org.example;

import org.example.entities.*;
import org.example.enums.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.example.conexion.ConexionBD;
import org.example.dao.EmpresaDAO;
import org.example.entities.Empresa;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        Pais estadosUnidos = new Pais("Estados Unidos");
        Provincia nuevoMexico = new Provincia("Nuevo Mexico", estadosUnidos);
        Localidad albuquerque = new Localidad("Albuquerque", nuevoMexico);
        Domicilio domicilioSucursal = new Domicilio("Negra Arroyo Lane", 123, 5501, albuquerque);

        Sucursal sucursal = new Sucursal("Sucursal Centro", LocalTime.of(9, 0), LocalTime.of(20, 0), domicilioSucursal, new HashSet<Categoria>(), new HashSet<Promocion>());

        Empresa empresa = new Empresa("Buen Sabor S.A.", "Buen Sabor Sociedad Anónima", 30567891, new HashSet<>());
        empresa.getSucursales().add(sucursal);

        System.out.println("Empresa: " + empresa.getNombre() + " - CUIT: " + empresa.getCuit());
        System.out.println("Sucursal: " + sucursal.getNombre() + " - Dirección: " + domicilioSucursal.getCalle() + " " + domicilioSucursal.getNumero());


        Usuario usuarioCliente = new Usuario("auth0|abc123", "Walter White");
        Imagen imagenCliente = new Imagen("cliente.png");
        Set<Domicilio> domicilios = new HashSet<>();
        domicilios.add(new Domicilio("Los Alamos", 321, 5502, albuquerque));

        Set<Pedido> pedidos = new HashSet<>();

        Cliente cliente = new Cliente(
                "Walter",
                "White",
                "2614567890",
                "walter.white@email.com",
                LocalDate.of(1995, 5, 20),
                imagenCliente,
                usuarioCliente,
                domicilios,
                pedidos
        );

        System.out.println("Cliente: " + cliente.getNombre() + " " + cliente.getApellido());
        System.out.println("Usuario: " + cliente.getUsuario().getUsername());


        UnidadMedida unidad = new UnidadMedida("unidad");

        Imagen imagen = new Imagen("hamburguesa.png");

        ArticuloInsumo pan = new ArticuloInsumo("Pan", 200.0, 150.0, 100, 200, false);
        ArticuloInsumo carne = new ArticuloInsumo("Carne", 500.0, 450.0, 100, 150, true);
        ArticuloInsumo cheddar = new ArticuloInsumo("Cheddar", 300.0, 134.0, 100, 150, false);

        ArticuloManufacturado hamburguesa = new ArticuloManufacturado(
                "Hamburguesa Completa",
                2500.0,
                "Pan, carne, lechuga, tomate y cheddar",
                15,
                "Cocinar la carne y armar la hamburguesa"
        );

        Categoria categoria = new Categoria("Comidas Rápidas",hamburguesa);

        hamburguesa.setUnidadMedida(unidad);
        hamburguesa.getImagenes().add(imagen);

        hamburguesa.getDetalles().add(new ArticuloManufacturadoDetalle(1, pan));
        hamburguesa.getDetalles().add(new ArticuloManufacturadoDetalle(1, carne));
        hamburguesa.getDetalles().add(new ArticuloManufacturadoDetalle(1, cheddar));

        DetallePedido detalle = new DetallePedido(2,100.0, hamburguesa);

        Pedido pedido = new Pedido(LocalDate.now(),FormaPago.MERCADOPAGO, TipoEnvio.DELIVERY);


        pedido.agregarDetalle(detalle);
        pedidos.add(pedido);

        Factura factura = new Factura(LocalDate.now(), FormaPago.MERCADOPAGO, pedido.getTotal());

        System.out.println("Pedido de: " + cliente.getNombre());
        System.out.println("Total pedido: $" + pedido.getTotal());
        System.out.println("Factura total: $" + factura.getTotalVenta());

        Promocion promo = new Promocion(
                "Promo 2x1 Hamburguesa",
                LocalDate.now(),
                LocalDate.now().plusDays(30),
                LocalTime.of(18, 0),
                LocalTime.of(20, 0),
                "2x1 en hamburguesas",
                2500.0,
                TipoPromocion.HAPPY_HOUR,
                new HashSet<Imagen>(),
                new HashSet<Articulo>()
        );

        promo.getImagenes().add(imagenCliente);
        promo.getArticulos().add(hamburguesa);

        sucursal.getPromociones().add(promo);
        sucursal.getCategorias().add(categoria);

        System.out.println("Promoción activa: " + promo.getDenominacion() + " en la " + sucursal.getNombre());

        try (Connection conexion = ConexionBD.getConexion()) {
            if (conexion == null) {
                System.out.println("❌ No se pudo establecer la conexión.");
                return;
            }

            EmpresaDAO dao = new EmpresaDAO();


            Empresa nueva = new Empresa();
            nueva.setNombre("MiEmpresa SRL");
            nueva.setRazonSocial("MiEmpresa Sociedad de Responsabilidad Limitada");
            nueva.setCuit(307123456);

            dao.save(conexion, nueva); // insert


            List<Empresa> lista = dao.getAll(conexion);
            System.out.println("📋 Empresas en la base:");
            for (Empresa e : lista) {
                System.out.println("🔹 ID: " + e.getId() + " | Nombre: " + e.getNombre());
            }


            Empresa buscada = dao.getById(conexion, Long.valueOf(nueva.getId()));
            if (buscada != null) {
                System.out.println("🔍 Empresa encontrada: " + buscada.getNombre());
            }


            buscada.setNombre("Empresa Actualizada SRL");
            dao.update(conexion, buscada);


            dao.delete(conexion, Long.valueOf(buscada.getId()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



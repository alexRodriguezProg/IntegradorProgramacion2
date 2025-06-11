package org.example.dao;

import org.example.entities.Empresa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDAO {

    public Empresa getById(Connection conex, Long id) throws SQLException {
        String sql = "SELECT * FROM empresa WHERE id = ?";
        try (PreparedStatement ps = conex.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                }
            }
        }
        return null;
    }

    public List<Empresa> getAll(Connection conex) throws SQLException {
        String sql = "SELECT * FROM empresa";
        List<Empresa> lista = new ArrayList<>();

        try (PreparedStatement ps = conex.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapResultSet(rs));
            }
        }
        return lista;
    }

    public void save(Connection conex, Empresa empresa) throws SQLException {
        String sql = "INSERT INTO empresa (nombre, razon_social, cuit) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conex.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, empresa.getNombre());
            ps.setString(2, empresa.getRazonSocial());
            ps.setInt(3, empresa.getCuit());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    empresa.setId((int) keys.getLong(1));
                    System.out.println("✅ Empresa insertada con ID: " + empresa.getId());
                }
            }
        }
    }

    public void update(Connection conex, Empresa empresa) throws SQLException {
        String sql = "UPDATE empresa SET nombre = ?, razon_social = ?, cuit = ? WHERE id = ?";

        try (PreparedStatement ps = conex.prepareStatement(sql)) {
            ps.setString(1, empresa.getNombre());
            ps.setString(2, empresa.getRazonSocial());
            ps.setInt(3, empresa.getCuit());
            ps.setLong(4, empresa.getId());

            int filas = ps.executeUpdate();
            System.out.println("🟡 Filas actualizadas: " + filas);
        }
    }

    public void delete(Connection conex, Long id) throws SQLException {
        String sql = "DELETE FROM empresa WHERE id = ?";

        try (PreparedStatement ps = conex.prepareStatement(sql)) {
            ps.setLong(1, id);
            int filas = ps.executeUpdate();
            System.out.println("🔴 Filas eliminadas: " + filas);
        }
    }

    private Empresa mapResultSet(ResultSet rs) throws SQLException {
        Empresa e = new Empresa();
        e.setId((int) rs.getLong("id"));
        e.setNombre(rs.getString("nombre"));
        e.setRazonSocial(rs.getString("razon_social"));
        e.setCuit(rs.getInt("cuit"));
        return e;
    }
}

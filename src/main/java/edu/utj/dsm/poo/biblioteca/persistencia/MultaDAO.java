package edu.utj.dsm.poo.biblioteca.persistencia;

import edu.utj.dsm.poo.biblioteca.modelo.Multa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MultaDAO {

    public boolean insertar(Multa multa) {
        String sql = "INSERT INTO multas (usuario, monto, motivo, estado) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, multa.getUsuario());
            ps.setDouble(2, multa.getMonto());
            ps.setString(3, multa.getMotivo());
            ps.setString(4, multa.getEstado());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar multa: " + e.getMessage());
            return false;
        }
    }

    public List<Multa> obtenerTodos() {
        List<Multa> lista = new ArrayList<>();
        String sql = "SELECT * FROM multas";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Multa multa = new Multa();
                multa.setId(rs.getInt("id"));
                multa.setUsuario(rs.getString("usuario"));
                multa.setMonto(rs.getDouble("monto"));
                multa.setMotivo(rs.getString("motivo"));
                multa.setEstado(rs.getString("estado"));
                lista.add(multa);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener multas: " + e.getMessage());
        }
        return lista;
    }

    public boolean actualizar(Multa multa) {
        String sql = "UPDATE multas SET usuario=?, monto=?, motivo=?, estado=? WHERE id=?";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, multa.getUsuario());
            ps.setDouble(2, multa.getMonto());
            ps.setString(3, multa.getMotivo());
            ps.setString(4, multa.getEstado());
            ps.setInt(5, multa.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar multa: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM multas WHERE id=?";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar multa: " + e.getMessage());
            return false;
        }
    }
}
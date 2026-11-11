package edu.utj.dsm.poo.biblioteca.persistencia;

import edu.utj.dsm.poo.biblioteca.modelo.Prestamo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {

    public boolean insertar(Prestamo prestamo) {
        String sql = "INSERT INTO prestamos (usuario, libro, fecha_prestamo, fecha_devolucion) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, prestamo.getUsuario());
            ps.setString(2, prestamo.getLibro());
            ps.setString(3, prestamo.getFechaPrestamo());
            ps.setString(4, prestamo.getFechaDevolucion());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar préstamo: " + e.getMessage());
            return false;
        }
    }

    public List<Prestamo> obtenerTodos() {
        List<Prestamo> lista = new ArrayList<>();
        String sql = "SELECT * FROM prestamos";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Prestamo p = new Prestamo();
                p.setId(rs.getInt("id"));
                p.setUsuario(rs.getString("usuario"));
                p.setLibro(rs.getString("libro"));
                p.setFechaPrestamo(rs.getString("fecha_prestamo"));
                p.setFechaDevolucion(rs.getString("fecha_devolucion"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener préstamos: " + e.getMessage());
        }
        return lista;
    }

    public boolean actualizar(Prestamo prestamo) {
        String sql = "UPDATE prestamos SET usuario=?, libro=?, fecha_prestamo=?, fecha_devolucion=? WHERE id=?";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, prestamo.getUsuario());
            ps.setString(2, prestamo.getLibro());
            ps.setString(3, prestamo.getFechaPrestamo());
            ps.setString(4, prestamo.getFechaDevolucion());
            ps.setInt(5, prestamo.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar préstamo: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM prestamos WHERE id=?";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar préstamo: " + e.getMessage());
            return false;
        }
    }
}
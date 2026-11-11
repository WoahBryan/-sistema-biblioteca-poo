package edu.utj.dsm.poo.biblioteca.persistencia;

import edu.utj.dsm.poo.biblioteca.modelo.Editorial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EditorialDAO {

    public boolean insertar(Editorial editorial) {
        String sql = "INSERT INTO editoriales (nombre, pais) VALUES (?, ?)";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, editorial.getNombre());
            ps.setString(2, editorial.getPais());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar editorial: " + e.getMessage());
            return false;
        }
    }

    public List<Editorial> obtenerTodos() {
        List<Editorial> lista = new ArrayList<>();
        String sql = "SELECT * FROM editoriales";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Editorial editorial = new Editorial();
                editorial.setId(rs.getInt("id"));
                editorial.setNombre(rs.getString("nombre"));
                editorial.setPais(rs.getString("pais"));
                lista.add(editorial);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener editoriales: " + e.getMessage());
        }
        return lista;
    }

    public boolean actualizar(Editorial editorial) {
        String sql = "UPDATE editoriales SET nombre=?, pais=? WHERE id=?";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, editorial.getNombre());
            ps.setString(2, editorial.getPais());
            ps.setInt(3, editorial.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar editorial: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM editoriales WHERE id=?";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar editorial: " + e.getMessage());
            return false;
        }
    }
}
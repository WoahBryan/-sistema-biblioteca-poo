package edu.utj.dsm.poo.biblioteca.persistencia;

import edu.utj.dsm.poo.biblioteca.modelo.Revista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
// Actualizacion del codigo
public class RevistaDAO {

    public boolean insertar(Revista revista) {
        String sql = "INSERT INTO revistas (titulo, editorial, issn, numero, periodicidad) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, revista.getTitulo());
            ps.setString(2, revista.getEditorial());
            ps.setString(3, revista.getIssn());
            ps.setInt(4, revista.getNumero());
            ps.setString(5, revista.getPeriodicidad());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar revista: " + e.getMessage());
            return false;
        }
    }

    public List<Revista> obtenerTodos() {
        List<Revista> lista = new ArrayList<>();
        String sql = "SELECT * FROM revistas";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Revista revista = new Revista();
                revista.setId(rs.getInt("id"));
                revista.setTitulo(rs.getString("titulo"));
                revista.setEditorial(rs.getString("editorial"));
                revista.setIssn(rs.getString("issn"));
                revista.setNumero(rs.getInt("numero"));
                revista.setPeriodicidad(rs.getString("periodicidad"));
                lista.add(revista);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener revistas: " + e.getMessage());
        }
        return lista;
    }

    public boolean actualizar(Revista revista) {
        String sql = "UPDATE revistas SET titulo=?, editorial=?, issn=?, numero=?, periodicidad=? WHERE id=?";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, revista.getTitulo());
            ps.setString(2, revista.getEditorial());
            ps.setString(3, revista.getIssn());
            ps.setInt(4, revista.getNumero());
            ps.setString(5, revista.getPeriodicidad());
            ps.setInt(6, revista.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar revista: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM revistas WHERE id=?";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar revista: " + e.getMessage());
            return false;
        }
    }
}

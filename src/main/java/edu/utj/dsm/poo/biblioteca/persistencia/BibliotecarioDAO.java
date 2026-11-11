package edu.utj.dsm.poo.biblioteca.persistencia;

import edu.utj.dsm.poo.biblioteca.modelo.Bibliotecario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BibliotecarioDAO {

    public boolean insertar(Bibliotecario bibliotecario) {
        String sql = "INSERT INTO bibliotecarios (nombre, primer_apellido, turno, area, rutaImagen) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, bibliotecario.getNombre());
            ps.setString(2, bibliotecario.getPrimerApellido());
            ps.setString(3, bibliotecario.getTurno());
            ps.setString(4, bibliotecario.getArea());
            ps.setString(5, bibliotecario.getFoto());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar bibliotecario: " + e.getMessage());
            return false;
        }
    }

    public List<Bibliotecario> obtenerTodos() {
        List<Bibliotecario> lista = new ArrayList<>();
        String sql = "SELECT * FROM bibliotecarios";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Bibliotecario b = new Bibliotecario();
                b.setId(rs.getInt("id"));
                b.setNombre(rs.getString("nombre"));
                b.setPrimerApellido(rs.getString("primer_apellido"));
                b.setTurno(rs.getString("turno"));
                b.setArea(rs.getString("area"));
                b.setFoto(rs.getString("rutaImagen"));
                lista.add(b);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener bibliotecarios: " + e.getMessage());
        }
        return lista;
    }

    public boolean actualizar(Bibliotecario bibliotecario) {
        String sql = "UPDATE bibliotecarios SET nombre=?, primer_apellido=?, turno=?, area=?, rutaImagen=? WHERE id=?";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, bibliotecario.getNombre());
            ps.setString(2, bibliotecario.getPrimerApellido());
            ps.setString(3, bibliotecario.getTurno());
            ps.setString(4, bibliotecario.getArea());
            ps.setString(5, bibliotecario.getFoto());
            ps.setInt(6, bibliotecario.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar bibliotecario: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM bibliotecarios WHERE id=?";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar bibliotecario: " + e.getMessage());
            return false;
        }
    }
}
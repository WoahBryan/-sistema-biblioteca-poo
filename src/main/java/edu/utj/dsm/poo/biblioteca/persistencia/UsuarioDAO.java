package edu.utj.dsm.poo.biblioteca.persistencia;

import edu.utj.dsm.poo.biblioteca.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public boolean insertar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, primerApellido, numeroCredencial, estado) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getPrimerApellido());
            ps.setString(3, usuario.getNumeroCredencial());
            ps.setString(4, usuario.getEstado());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }

    public List<Usuario> obtenerTodos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setPrimerApellido(rs.getString("primerApellido"));
                usuario.setNumeroCredencial(rs.getString("numeroCredencial"));
                usuario.setEstado(rs.getString("estado"));
                lista.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener usuarios: " + e.getMessage());
        }
        return lista;
    }

    public boolean actualizar(Usuario usuario) {
        String sql = "UPDATE usuarios SET nombre=?, primerApellido=?, numeroCredencial=?, estado=? WHERE id=?";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getPrimerApellido());
            ps.setString(3, usuario.getNumeroCredencial());
            ps.setString(4, usuario.getEstado());
            ps.setInt(5, usuario.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM usuarios WHERE id=?";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }
}

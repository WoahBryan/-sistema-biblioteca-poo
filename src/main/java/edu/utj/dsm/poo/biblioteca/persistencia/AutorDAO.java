package edu.utj.dsm.poo.biblioteca.persistencia;

import edu.utj.dsm.poo.biblioteca.modelo.Autor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {

    public boolean insertar(Autor autor) {
        String sql = "INSERT INTO autores (nombre, primer_apellido, nacionalidad, seudonimo, rutaImagen) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, autor.getNombre());
            ps.setString(2, autor.getPrimerApellido());
            ps.setString(3, autor.getNacionalidad());
            ps.setString(4, autor.getSeudonimo());
            ps.setString(5, autor.getFoto()); // Ruta de la imagen
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar autor: " + e.getMessage());
            return false;
        }
    }

    public List<Autor> obtenerTodos() {
        List<Autor> lista = new ArrayList<>();
        String sql = "SELECT * FROM autores";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Autor autor = new Autor();
                autor.setId(rs.getInt("id"));
                autor.setNombre(rs.getString("nombre"));
                autor.setPrimerApellido(rs.getString("primer_apellido"));
                autor.setNacionalidad(rs.getString("nacionalidad"));
                autor.setSeudonimo(rs.getString("seudonimo"));
                autor.setFoto(rs.getString("rutaImagen")); // Recuperar ruta de la imagen
                lista.add(autor);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener autores: " + e.getMessage());
        }
        return lista;
    }

    public boolean actualizar(Autor autor) {
        String sql = "UPDATE autores SET nombre=?, primer_apellido=?, nacionalidad=?, seudonimo=?, rutaImagen=? WHERE id=?";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, autor.getNombre());
            ps.setString(2, autor.getPrimerApellido());
            ps.setString(3, autor.getNacionalidad());
            ps.setString(4, autor.getSeudonimo());
            ps.setString(5, autor.getFoto());
            ps.setInt(6, autor.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar autor: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM autores WHERE id=?";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar autor: " + e.getMessage());
            return false;
        }
    }
}
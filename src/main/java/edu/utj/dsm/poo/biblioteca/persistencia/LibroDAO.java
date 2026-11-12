package edu.utj.dsm.poo.biblioteca.persistencia;

import edu.utj.dsm.poo.biblioteca.modelo.Libro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LibroDAO implements Listable<Libro> {

    @Override
    public boolean agregar(Libro libro) {
        String sql = "INSERT INTO libros (titulo, anioPublicacion, disponible, isbn, numeroPaginas, ruta_imagen_1, ruta_imagen_2, ruta_imagen_3) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, libro.getTitulo());
            ps.setInt(2, libro.getAnioPublicacion());
            ps.setBoolean(3, libro.isDisponible());
            ps.setString(4, libro.getIsbn());
            ps.setInt(5, libro.getNumeroPaginas());
            ps.setString(6, libro.getRutaImagen1());
            ps.setString(7, libro.getRutaImagen2());
            ps.setString(8, libro.getRutaImagen3());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al guardar el libro: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizar(Libro libro, int posicion) {
        String sql = "UPDATE libros SET titulo=?, anioPublicacion=?, disponible=?, isbn=?, numeroPaginas=?, "
                   + "ruta_imagen_1=?, ruta_imagen_2=?, ruta_imagen_3=? WHERE id=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, libro.getTitulo());
            ps.setInt(2, libro.getAnioPublicacion());
            ps.setBoolean(3, libro.isDisponible());
            ps.setString(4, libro.getIsbn());
            ps.setInt(5, libro.getNumeroPaginas());
            ps.setString(6, libro.getRutaImagen1());
            ps.setString(7, libro.getRutaImagen2());
            ps.setString(8, libro.getRutaImagen3());
            ps.setInt(9, libro.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar el libro: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(Libro libro) {
        String sql = "DELETE FROM libros WHERE id=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, libro.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar el libro: " + e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<Libro> listar() {
        ArrayList<Libro> lista = new ArrayList<>();
        String sql = "SELECT * FROM libros";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Libro l = new Libro();
                l.setId(rs.getInt("id"));
                l.setTitulo(rs.getString("titulo"));
                l.setAnioPublicacion(rs.getInt("anioPublicacion"));
                l.setDisponible(rs.getBoolean("disponible"));
                l.setIsbn(rs.getString("isbn"));
                l.setNumeroPaginas(rs.getInt("numeroPaginas"));
                l.setRutaImagen1(rs.getString("ruta_imagen_1"));
                l.setRutaImagen2(rs.getString("ruta_imagen_2"));
                l.setRutaImagen3(rs.getString("ruta_imagen_3"));
                lista.add(l);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar libros: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public ArrayList<Libro> consultar(String dato) {
        return new ArrayList<>();
    }

    @Override
    public Libro traer(String dato) {
        return null;
    }
}
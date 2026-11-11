//CONTRERAS MARTINEZ BRYAN DANIEL
package edu.utj.dsm.poo.biblioteca.persistencia;

import edu.utj.dsm.poo.biblioteca.modelo.Cuenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDAO {

    public Cuenta autenticar(String usuarioOCorreo, String contrasena) {
        String sql = "SELECT c.idCuenta, c.nombre, c.correo, c.idRol, r.nombre AS nombreRol, c.estado " +
                     "FROM cuenta c " +
                     "INNER JOIN rol r ON c.idRol = r.idRol " +
                     "WHERE (c.correo = ? OR c.nombre = ?) AND c.contrasena = SHA2(?, 256)";
        
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, usuarioOCorreo);
            ps.setString(2, usuarioOCorreo);
            ps.setString(3, contrasena);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String estado = rs.getString("estado");
                    if ("Inactivo".equalsIgnoreCase(estado)) {
                        return null; // La cuenta está inactiva
                    }
                    Cuenta cuenta = new Cuenta();
                    cuenta.setIdCuenta(rs.getInt("idCuenta"));
                    cuenta.setNombre(rs.getString("nombre"));
                    cuenta.setCorreo(rs.getString("correo"));
                    cuenta.setIdRol(rs.getInt("idRol"));
                    cuenta.setNombreRol(rs.getString("nombreRol"));
                    cuenta.setEstado(estado);
                    
                    registrarInicioSesion(cuenta.getIdCuenta());
                    return cuenta;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en la autenticación: " + e.getMessage());
        }
        return null;
    }

    private void registrarInicioSesion(int idCuenta) {
        String sql = "INSERT INTO sesion (idCuenta, fechaInicio) VALUES (?, NOW())";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCuenta);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al registrar inicio de sesión: " + e.getMessage());
        }
    }

    public void registrarCierreSesion(int idCuenta) {
        String sql = "UPDATE sesion SET fechaCierre = NOW() WHERE idCuenta = ? AND fechaCierre IS NULL";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCuenta);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al registrar cierre de sesión: " + e.getMessage());
        }
    }
}
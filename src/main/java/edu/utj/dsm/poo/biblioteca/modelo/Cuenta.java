//CONTRERAS MARTINEZ BRYAN DANIEL
package edu.utj.dsm.poo.biblioteca.modelo;

public class Cuenta {
    private int idCuenta;
    private String nombre;
    private String correo;
    private int idRol;
    private String nombreRol;
    private String estado;

    public Cuenta() {}

    public Cuenta(int idCuenta, String nombre, String correo, int idRol, String nombreRol, String estado) {
        this.idCuenta = idCuenta;
        this.nombre = nombre;
        this.correo = correo;
        this.idRol = idRol;
        this.nombreRol = nombreRol;
        this.estado = estado;
    }

    public int getIdCuenta() { return idCuenta; }
    public void setIdCuenta(int idCuenta) { this.idCuenta = idCuenta; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public int getIdRol() { return idRol; }
    public void setIdRol(int idRol) { this.idRol = idRol; }

    public String getNombreRol() { return nombreRol; }
    public void setNombreRol(String nombreRol) { this.nombreRol = nombreRol; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
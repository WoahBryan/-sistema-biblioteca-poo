package edu.utj.dsm.poo.biblioteca.modelo;

import java.util.Date;

/**
 * Clase que representa la transacción del préstamo de un material a un usuario.
 * 
 * @author CONTRERAS MARTINEZ BRYAN DANIEL
 */
public class Prestamo {
    private int id;
    private Date fechaSalida;
    private Usuario usuario;
    private Material material;

    /**
     * Constructor vacío.
     */
    public Prestamo() {
        this.id = 0;
        this.fechaSalida = new Date();
        this.usuario = new Usuario();
    }

    /**
     * Constructor con atributos mínimos.
     * 
     * @param id Identificador de la transacción.
     * @param usuario Usuario que solicita el préstamo.
     * @param material Material prestado.
     */
    public Prestamo(int id, Usuario usuario, Material material) {
        this.id = id;
        this.fechaSalida = new Date();
        this.usuario = usuario;
        this.material = material;
    }

    /**
     * Obtiene el ID.
     * @return identificador entero.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID.
     * @param id identificador a asignar.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene la fecha de salida.
     * @return fecha del préstamo.
     */
    public Date getFechaSalida() {
        return fechaSalida;
    }

    /**
     * Establece la fecha de salida.
     * @param fechaSalida fecha a asignar.
     */
    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    /**
     * Obtiene el usuario asociado.
     * @return objeto Usuario.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario asociado.
     * @param usuario objeto a asignar.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene el material prestado.
     * @return objeto Material.
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Establece el material prestado.
     * @param material objeto a asignar.
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Prestamo other = (Prestamo) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Prestamo{" + "id=" + id + ", fechaSalida=" + fechaSalida + ", usuario=" + usuario + ", material=" + material + '}';
    }
}
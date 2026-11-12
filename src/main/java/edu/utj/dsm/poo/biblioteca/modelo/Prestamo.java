package edu.utj.dsm.poo.biblioteca.modelo;

/**
 * Clase que representa la transacción del préstamo de un material a un usuario.
 * 
 * @author CONTRERAS MARTINEZ BRYAN DANIEL
 */
public class Prestamo {
    private int id;
    private String fechaPrestamo;
    private String fechaDevolucion;
    private String usuario;
    private String libro;

    /**
     * Constructor vacío.
     */
    public Prestamo() {
        this.id = 0;
        this.fechaPrestamo = "";
        this.fechaDevolucion = "";
        this.usuario = "";
        this.libro = "";
    }

    /**
     * Constructor con atributos mínimos.
     * 
     * @param id Identificador de la transacción.
     * @param usuario Nombre del usuario que solicita el préstamo.
     * @param libro Título del libro o material prestado.
     * @param fechaPrestamo Fecha en la que se realiza el préstamo.
     * @param fechaDevolucion Fecha límite de devolución.
     */
    public Prestamo(int id, String usuario, String libro, String fechaPrestamo, String fechaDevolucion) {
        this.id = id;
        this.usuario = usuario;
        this.libro = libro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
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
     * Obtiene la fecha de préstamo.
     * @return fecha del préstamo.
     */
    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    /**
     * Establece la fecha de préstamo.
     * @param fechaPrestamo fecha a asignar.
     */
    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    /**
     * Obtiene la fecha de devolución.
     * @return fecha de devolución.
     */
    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    /**
     * Establece la fecha de devolución.
     * @param fechaDevolucion fecha a asignar.
     */
    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    /**
     * Obtiene el usuario asociado.
     * @return nombre del usuario.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario asociado.
     * @param usuario nombre a asignar.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene el material prestado.
     * @return título del libro.
     */
    public String getLibro() {
        return libro;
    }

    /**
     * Establece el material prestado.
     * @param libro título a asignar.
     */
    public void setLibro(String libro) {
        this.libro = libro;
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
        return "Prestamo{" + "id=" + id + ", fechaPrestamo=" + fechaPrestamo + ", fechaDevolucion=" + fechaDevolucion + ", usuario=" + usuario + ", libro=" + libro + '}';
    }
}
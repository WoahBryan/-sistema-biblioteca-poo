package edu.utj.dsm.poo.biblioteca.modelo;

/**
 * Clase que representa una sanción impuesta por retraso o daño.
 * 
 * @author CONTRERAS MARTINEZ BRYAN DANIEL
 */
public class Multa {
    private int id;
    private String usuario;
    private double monto;
    private String motivo;
    private String estado;
    private boolean pagada;
    private Prestamo prestamo;

    /**
     * Constructor vacío.
     */
    public Multa() {
        this.id = 0;
        this.usuario = "";
        this.monto = 0.0;
        this.motivo = "";
        this.estado = "";
        this.pagada = false;
        // this.prestamo = new Prestamo(); // Comentado por si no tienes la clase Prestamo lista aún, quítale las diagonales si sí la tienes
    }

    // --- NUEVOS GETTERS Y SETTERS PARA LA PANTALLA ---

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // --- GETTERS Y SETTERS ORIGINALES ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public boolean isPagada() {
        return pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.id;
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
        final Multa other = (Multa) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Multa{" + "id=" + id + ", usuario=" + usuario + ", monto=" + monto + ", motivo=" + motivo + ", estado=" + estado + '}';
    }
}
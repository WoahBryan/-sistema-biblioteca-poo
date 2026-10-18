package edu.utj.dsm.poo.biblioteca.modelo;

/**
 * Clase que representa una sanción impuesta por retraso o daño.
 * 
 * @author CONTRERAS MARTINEZ BRYAN DANIEL
 */
public class Multa {
    private int id;
    private double monto;
    private boolean pagada;
    private Prestamo prestamo;

    /**
     * Constructor vacío.
     */
    public Multa() {
        this.id = 0;
        this.monto = 0.0;
        this.pagada = false;
        this.prestamo = new Prestamo();
    }

    /**
     * Constructor con atributos mínimos.
     * 
     * @param id Identificador de la multa.
     * @param monto Cantidad a pagar.
     * @param prestamo Préstamo que generó la multa.
     */
    public Multa(int id, double monto, Prestamo prestamo) {
        this.id = id;
        this.monto = monto;
        this.prestamo = prestamo;
        this.pagada = false;
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
     * Obtiene el monto.
     * @return cantidad adeudada.
     */
    public double getMonto() {
        return monto;
    }

    /**
     * Establece el monto.
     * @param monto cantidad a asignar.
     */
    public void setMonto(double monto) {
        this.monto = monto;
    }

    /**
     * Verifica si está pagada.
     * @return true si ya fue liquidada.
     */
    public boolean isPagada() {
        return pagada;
    }

    /**
     * Establece el estado de pago.
     * @param pagada estado a asignar.
     */
    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }

    /**
     * Obtiene el préstamo asociado.
     * @return objeto Prestamo.
     */
    public Prestamo getPrestamo() {
        return prestamo;
    }

    /**
     * Establece el préstamo asociado.
     * @param prestamo objeto a asignar.
     */
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
        return "Multa{" + "id=" + id + ", monto=" + monto + ", pagada=" + pagada + ", prestamo=" + prestamo + '}';
    }
}
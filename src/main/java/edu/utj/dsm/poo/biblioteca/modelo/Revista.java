package edu.utj.dsm.poo.biblioteca.modelo;

/**
 * Clase que representa una publicación periódica.
 * Se define como final.
 * 
 * @author CONTRERAS MARTINEZ BRYAN DANIEL
 */
public final class Revista extends Material {
    private int volumen;
    private int numeroEdicion;
    private String frecuencia;
    
    // Variables agregadas para compatibilidad con la pantalla y base de datos
    private String editorial;
    private String issn;
    private int numero;
    private String periodicidad;

    /**
     * Constructor vacío.
     */
    public Revista() {
        super();
        this.volumen = 0;
        this.numeroEdicion = 0;
        this.frecuencia = "";
        this.editorial = "";
        this.issn = "";
        this.numero = 0;
        this.periodicidad = "";
    }

    /**
     * Constructor con atributos mínimos.
     * 
     * @param id Identificador entero.
     * @param titulo Título de la revista.
     * @param numeroEdicion Número de edición.
     */
    public Revista(int id, String titulo, int numeroEdicion) {
        super(id, titulo);
        this.numeroEdicion = numeroEdicion;
        this.volumen = 0;
        this.frecuencia = "";
        this.editorial = "";
        this.issn = "";
        this.numero = 0;
        this.periodicidad = "";
    }

    // --- NUEVOS GETTERS Y SETTERS PARA LA PANTALLA ---

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(String periodicidad) {
        this.periodicidad = periodicidad;
    }

    // --- GETTERS Y SETTERS ORIGINALES ---

    /**
     * Obtiene el volumen.
     * @return volumen de la revista.
     */
    public int getVolumen() {
        return volumen;
    }

    /**
     * Establece el volumen.
     * @param volumen volumen a asignar.
     */
    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }

    /**
     * Obtiene el número de edición.
     * @return edición.
     */
    public int getNumeroEdicion() {
        return numeroEdicion;
    }

    /**
     * Establece el número de edición.
     * @param numeroEdicion edición a asignar.
     */
    public void setNumeroEdicion(int numeroEdicion) {
        this.numeroEdicion = numeroEdicion;
    }

    /**
     * Obtiene la frecuencia de publicación.
     * @return frecuencia (ej. Mensual, Semestral).
     */
    public String getFrecuencia() {
        return frecuencia;
    }

    /**
     * Establece la frecuencia.
     * @param frecuencia frecuencia a asignar.
     */
    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    @Override
    public String toString() {
        return super.toString() + " Revista{" + "volumen=" + volumen + ", numeroEdicion=" + numeroEdicion + ", frecuencia=" + frecuencia + ", editorial=" + editorial + ", issn=" + issn + ", numero=" + numero + ", periodicidad=" + periodicidad + '}';
    }
}
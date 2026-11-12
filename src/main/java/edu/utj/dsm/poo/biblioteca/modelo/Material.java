package edu.utj.dsm.poo.biblioteca.modelo;

/**
 * Clase base abstracta que representa cualquier elemento prestable en la biblioteca.
 * 
 * @author CONTRERAS MARTINEZ BRYAN DANIEL
 */
public abstract class Material {
    private int id;
    private String titulo;
    private int anioPublicacion;
    private boolean disponible;

    /**
     * Constructor vacío requerido por la rúbrica.
     */
    public Material() {
        this.id = 0;
        this.titulo = "";
        this.anioPublicacion = 0;
        this.disponible = true;
    }

    /**
     * Constructor con los atributos mínimos.
     * 
     * @param id Identificador entero único del material.
     * @param titulo Título de la obra o publicación.
     */
    public Material(int id, String titulo) {
        this.id = id;
        this.titulo = titulo;
        this.anioPublicacion = 0;
        this.disponible = true;
    }

    /**
     * Obtiene el identificador del material.
     * @return id entero.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador del material.
     * @param id identificador a asignar.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el título.
     * @return título del material.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título.
     * @param titulo título a asignar.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene el año de publicación.
     * @return año de publicación.
     */
    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    /**
     * Establece el año de publicación.
     * @param anioPublicacion año a asignar.
     */
    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    /**
     * Verifica si el material está disponible para préstamo.
     * @return true si está disponible.
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * Establece la disponibilidad del material.
     * @param disponible estado a asignar.
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.id;
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
        final Material other = (Material) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Material{" + "id=" + id + ", titulo=" + titulo + ", anioPublicacion=" + anioPublicacion + ", disponible=" + disponible + '}';
    }
}
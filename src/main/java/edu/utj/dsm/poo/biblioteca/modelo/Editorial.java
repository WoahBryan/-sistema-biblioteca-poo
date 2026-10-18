package edu.utj.dsm.poo.biblioteca.modelo;

/**
 * Clase que representa a la empresa publicadora.
 * 
 * @author CONTRERAS MARTINEZ BRYAN DANIEL
 */
public class Editorial {
    private int id;
    private String nombre;
    private String pais;

    /**
     * Constructor vacío.
     */
    public Editorial() {
        this.id = 0;
        this.nombre = "";
        this.pais = "";
    }

    /**
     * Constructor con atributos mínimos.
     * 
     * @param id Identificador de la editorial.
     * @param nombre Nombre comercial.
     */
    public Editorial(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.pais = "";
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
     * Obtiene el nombre.
     * @return nombre de la editorial.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre.
     * @param nombre nombre a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el país.
     * @return país de origen.
     */
    public String getPais() {
        return pais;
    }

    /**
     * Establece el país.
     * @param pais país a asignar.
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id;
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
        final Editorial other = (Editorial) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Editorial{" + "id=" + id + ", nombre=" + nombre + ", pais=" + pais + '}';
    }
}
package edu.utj.dsm.poo.biblioteca.modelo;

/**
 * Clase que representa al creador intelectual de un material de la biblioteca.
 * Se define como final para impedir la creación de subclases.
 * 
 * @author CONTRERAS MARTINEZ BRYAN DANIEL
 */
public final class Autor extends Persona {
    private String nacionalidad;
    private String seudonimo;

    /**
     * Constructor vacío requerido por la rúbrica.
     */
    public Autor() {
        super();
        this.nacionalidad = "";
        this.seudonimo = "";
    }

    /**
     * Constructor con los atributos mínimos indispensables.
     * 
     * @param id Identificador único de la persona.
     * @param nombre Nombre de pila.
     * @param primerApellido Primer apellido.
     * @param nacionalidad País de origen del autor.
     */
    public Autor(int id, String nombre, String primerApellido, String nacionalidad) {
        super(id, nombre, primerApellido);
        this.nacionalidad = nacionalidad;
        this.seudonimo = "";
    }

    /**
     * Obtiene la nacionalidad del autor.
     * @return nacionalidad del autor.
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Establece la nacionalidad del autor.
     * @param nacionalidad país a asignar.
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * Obtiene el seudónimo del autor.
     * @return seudónimo utilizado.
     */
    public String getSeudonimo() {
        return seudonimo;
    }

    /**
     * Establece el seudónimo del autor.
     * @param seudonimo nombre artístico a asignar.
     */
    public void setSeudonimo(String seudonimo) {
        this.seudonimo = seudonimo;
    }

    @Override
    public String toString() {
        return super.toString() + " Autor{" + "nacionalidad=" + nacionalidad + ", seudonimo=" + seudonimo + '}';
    }
}

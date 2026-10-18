package edu.utj.dsm.poo.biblioteca.modelo;

import java.util.Date;
import java.util.Objects;

/**
 * Clase base abstracta que representa a una entidad humana dentro del sistema de la biblioteca.
 * Sirve como generalización para usuarios, bibliotecarios y autores.
 * 
 * @author CONTRERAS MARTINEZ BRYAN DANIEL
 */
public abstract class Persona {
    private int id;
    private String rfc;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private Date nacimiento;
    private String rutaImagen;

    /**
     * Constructor vacío requerido para frameworks e instanciación por defecto.
     */
    public Persona() {
        this.id = 0;
        this.rfc = "";
        this.nombre = "";
        this.primerApellido = "";
        this.segundoApellido = "";
        this.nacimiento = new Date();
        this.rutaImagen = "";
    }

    /**
     * Constructor con los atributos mínimos indispensables para que el objeto nazca válido.
     * 
     * @param id Identificador único primario de la persona.
     * @param nombre Nombre de pila de la persona.
     * @param primerApellido Primer apellido de la persona.
     */
    public Persona(int id, String nombre, String primerApellido) {
        this.id = id;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.rfc = "";
        this.segundoApellido = "";
        this.nacimiento = new Date();
        this.rutaImagen = "";
    }

    /**
     * Obtiene el identificador único de la persona.
     * @return id de la persona.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador único de la persona.
     * @param id Identificador entero a asignar.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el RFC de la persona.
     * @return RFC asignado.
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * Establece el RFC de la persona.
     * @param rfc Registro a asignar.
     */
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    /**
     * Obtiene el nombre de la persona.
     * @return nombre de la persona.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la persona.
     * @param nombre nombre a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el primer apellido de la persona.
     * @return primer apellido.
     */
    public String getPrimerApellido() {
        return primerApellido;
    }

    /**
     * Establece el primer apellido de la persona.
     * @param primerApellido primer apellido a asignar.
     */
    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    /**
     * Obtiene el segundo apellido de la persona.
     * @return segundo apellido.
     */
    public String getSegundoApellido() {
        return segundoApellido;
    }

    /**
     * Establece el segundo apellido de la persona.
     * @param segundoApellido segundo apellido a asignar.
     */
    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    /**
     * Obtiene la fecha de nacimiento.
     * @return fecha de nacimiento.
     */
    public Date getNacimiento() {
        return nacimiento;
    }

    /**
     * Establece la fecha de nacimiento.
     * @param nacimiento fecha a asignar.
     */
    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    /**
     * Obtiene la ruta de la imagen.
     * @return ruta de la imagen.
     */
    public String getRutaImagen() {
        return rutaImagen;
    }

    /**
     * Establece la ruta de la imagen.
     * @param rutaImagen ruta a asignar.
     */
    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Persona other = (Persona) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", rfc=" + rfc + ", nombre=" + nombre + ", primerApellido=" + primerApellido + ", segundoApellido=" + segundoApellido + ", nacimiento=" + nacimiento + ", rutaImagen=" + rutaImagen + '}';
    }
}
/*
 CONTRERAS MARTINEZ BRYAN DANIEL
 */
package edu.utj.dsm.poo.biblioteca.modelo;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Tazit
 */
public abstract class Persona {
    protected String rfc, nombre;// Por default los String inician en null
    protected String primerApellido, segundoApellido;
    protected  Date nacimiento;// Por default los Date inician en null
    protected String rutaImagen;
    /**
     * Sirve para inicializar los atributos 
     * O bien, para recibir datos externos de inicializacion
     */
    public Persona(){
     this.rfc="";
     this.nombre="";
     this.primerApellido="";
     this.segundoApellido="";
     this.nacimiento=new Date(2026, 9, 4);
    }
    
    //Set
    /**
     * Establece el RFC de la persona
     * @param rfc RFC a asginar
     */
    public void setRfc(String rfc){
        this.rfc= rfc;
    }
    /**
     * Establece el nombre de la persona
     * @param nombre nombre a asginar
     */
    public void setNombre(String nombre){
        this.nombre= nombre;
    }
    /**
     * Establece el primer apellido de la persona
     * @param primerApe primer apellido a asignar
     */
     public void setPrimerApe(String primerApe){
        this.primerApellido = primerApe;
    }
     /**
      * Establece el segundo apellido de la persona
      * @param segundoApe  segundo apelido a asignar.
      */
    
    public void setSegundoApellido(String segundoApe){
        this.segundoApellido= segundoApe;
    }
    /**
     * Establece la fecha de nacimiento de la persona
     * @param nacimiento nacimiento fecha a asignar
     */
    public void setNacimiento(Date nacimiento){
        this.nacimiento = nacimiento;
    }
    /**
     * Establece la ruta de la imagen de la persona
     * @param ruta ruta de la imagen
     */
    public void setRutaImagen(String ruta){
        this.rutaImagen = ruta;
    }
    
    
    //Get
    /**
     * Obtiene el RFC de la persona
     * @return RFC de la persona
     */
    public String getRfc(){
        return this.rfc;
    }
    
    /**
     * Obtiene el nombre de la persona
     * @return nombre de la persona
     */
    public String getNombre(){
        return this.nombre;
    }
    /**
     * Obtiene el primer apellido de la persona
     * @return primer apellido de la persona
     */
    public String getPrimerApellido(){
        return this.primerApellido;
    }
    /**
     * Obtiene el segundo apellido de la persona
     * @return segundo apellido de la persona
     */
    
    public String getSegundoApellido(){
        return this.segundoApellido;
    }
   /**
    * Obtiene la fecha de nacimiento de la persona
    * @return fecha de nacimiento de la persona
    */
    public Date getNacimiento(){
        return this.nacimiento;
    }
    /**
     * Obtiene la ruta de imagen de la persona
     * @return ruta de imagen
     */
    public String getRutaImagen(){
        return this.rutaImagen;
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.rfc);
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
        return Objects.equals(this.rfc, other.rfc);
    }

    @Override
    public String toString() {
        return "Persona{" + "rfc=" + rfc + ", nombre=" + nombre + ", primerApellido=" + primerApellido + ", segundoApellido=" + segundoApellido + ", nacimiento=" + nacimiento + ", rutaImagen=" + rutaImagen + '}';
    }
 
    
    
    
    
    
}

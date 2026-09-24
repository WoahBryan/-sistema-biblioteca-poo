/*
CONTRERAS MARTINEZ BRYAN DANIEL
 */
package edu.utj.dsm.poo.biblioteca.modelo;

/**
 *
 * @author Tazit
 */
public final class Autor extends Persona  {
    private String genero;
    private String nacionalidad;
    /**
     * Incializa los atributos propios del autor
     */
    public Autor(){
        super();
        this.genero = "";
        this.nacionalidad = "";
    }
    /**
     * Establece genero literario del autor
     * @param genero genero a asignar
     */
    public void setGenero(String genero){
        this.genero = genero;
    }
    /**
     * Obtiene genero literario del autor
     * @return genero literario
     */
    public String getGenero(){
        return this.genero;
    }
    /**
     * Establece la nacionalidad del autor
     * @param nacionalidad nacionalidad a asignar
     */
    public void setNacionalidad(String nacionalidad){
        this.nacionalidad = nacionalidad;
    }
    /**
     * Obtiene la nacionalidad del autor
     * @return nacionalidad del autor
     */
    public String getNacionalidad(){
        return this.nacionalidad;
    }

    @Override
    public String toString() {
        return super.toString()+"Autor{" + "genero=" + genero + ", nacionalidad=" + nacionalidad + '}';
    }
    
}

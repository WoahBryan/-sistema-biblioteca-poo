/*
 CONTRERAS MARTINEZ BRYAN DANIEL
 */
package edu.utj.dsm.poo.biblioteca.modelo;

/**
 *
 * @author Tazit
 */
public final class Usuario extends Persona {
  private String direccion;
  private String telefono;
  /**
   * Inicializa los atributos propios del usuario
   */
  public Usuario(){
      super();
      this.direccion = "";
      this.telefono = "";
  }
  /**
   * Establece la direccion del usuario
   * @param direccion direccion del usuario
   */
  public void setDireccion(String direccion){
      this.direccion = direccion;
  }
  /**
   * Obtiene la direccion del usuario
   * @return direccion del usuario
   */
  public String getDireccion(){
      return this.direccion;
  }
  /**
   * Establece el telefono del usuario
   * @param telefono telefono a asignar
   */
  public void setTelefono(String telefono){
      this.telefono = telefono;
  }
  /**
   * Obtiene el telefono del usuario
   * @return telefono del usuario
   */
  public String getTelefono(){
      return this.telefono;
  }
}

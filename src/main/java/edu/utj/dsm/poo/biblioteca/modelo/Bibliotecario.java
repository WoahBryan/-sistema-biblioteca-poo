/*
 CONTRERAS MARTINEZ BRYAN DANIEL
 */
package edu.utj.dsm.poo.biblioteca.modelo;

/**
 *
 * @author Tazit
 */
public final class Bibliotecario extends Persona {
     private String turno;
     private String area;
     /**
      * Incializa los atributos propios del bibliotecario
      */
     public Bibliotecario(){
         super();
         this.turno = "";
         this.area = "";
     }
     
     /**
      * Establece el turno del bibliotecario
      * @param turno turno a asignar
      */
     public void setTurno(String turno){
         this.turno = turno;
     }
     /**
      * Obtiene el turno del bibiotecario
      * @return turno asignado
      */
     public String getTurno(){
         return this.turno;
     }
     
     /**
      * Establece area asignada al bibliotecario
      * @param area area a asignar
      */
     public void setArea(String area){
         this.area = area;
     }
     /**
      * Obtiene area asignada al bibliotecario
      * @return area asignada
      */
     public String getArea(){
         return this.area;
     }
     
}

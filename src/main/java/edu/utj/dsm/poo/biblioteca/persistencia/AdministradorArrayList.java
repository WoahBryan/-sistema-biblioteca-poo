/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utj.dsm.poo.biblioteca.persistencia;

import java.util.ArrayList;

/**
 *
 * @author Tazit
 */
public class AdministradorArrayList implements Listable<Administrador>{

    private final ArrayList<Administrador> lista;

    public AdministradorArrayList() {
        this.lista = new ArrayList<>();
    }
    
    
    
    @Override
    public boolean agregar(Administrador elemento) {
        return lista.add(elemento);
    }

    @Override
    public boolean actualizar(Administrador elemento, int posicion) {
      if(lista.contains(elemento)){
       lista.set(posicion, elemento);
       return true;
      }
      return false;
    }

    @Override
    public boolean eliminar(Administrador elemento) {
        return lista.remove(elemento);
    }

    @Override
    public ArrayList<Administrador> listar() {
        return (ArrayList<Administrador>) lista.clone();
    }

    @Override
    public ArrayList<Administrador> consultar(String dato) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Administrador traer(String dato) {
       Administrador aux = new Administrador();
       aux.setRfc(dato);
       if(lista.contains(aux)){
       int posicion = lista.indexOf(aux);
       return lista.get(posicion);
       }
       return null;
    }

    
    
}

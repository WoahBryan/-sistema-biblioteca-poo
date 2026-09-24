/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.utj.dsm.poo.biblioteca.persistencia;

import java.util.ArrayList;

/**
 *
 * @author Tazit
 */
public interface Listable<T> {
    public boolean agregar(T elemento);
    public boolean actualizar(T elemento, int posicion);
    public boolean eliminar(T elemento);
    public ArrayList<T> listar();
    public ArrayList<T> consultar(String dato);
    public T traer(String dato);
    
}

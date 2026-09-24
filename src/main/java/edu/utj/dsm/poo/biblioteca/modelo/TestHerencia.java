/*
CONTRERAS MARTINEZ BRYAN DANIEL
 */
package edu.utj.dsm.poo.biblioteca.modelo;

import java.util.Scanner;

/**
 *
 * @author Tazit
 */
public class TestHerencia {
    private Scanner entrada = new Scanner(System.in);
    private Bibliotecario biblio = new Bibliotecario();
    private Autor autor = new Autor();
    private Usuario usuario = new Usuario();

    public void capturarDatosBibliotecario(){
        System.out.println("RFC: ");
        biblio.setRfc(entrada.next());
        System.out.println("Nombre: ");
        biblio.setNombre(entrada.next());
        System.out.println("Primer Apellido: ");
        biblio.setPrimerApellido(entrada.next());
        System.out.println("Turno: ");
        biblio.setTurno(entrada.next());
        System.out.println("Area: ");
        biblio.setArea(entrada.next());
    }

    public void mostrarDatosBibliotecario(){
        System.out.println("Datos del bibliotecario");
        System.out.println("RFC: "+biblio.getRfc());
        System.out.println("Nombre: "+biblio.getNombre() + " "+biblio.getPrimerApellido());
        System.out.println("Turno: " +biblio.getTurno());
        System.out.println("Area: "+biblio.getArea());
    }

    public void capturarDatosAutor(){
        System.out.println("RFC: ");
        autor.setRfc(entrada.next());
        System.out.println("Nombre: ");
        autor.setNombre(entrada.next());
        System.out.println("Primer Apellido: ");
        autor.setPrimerApellido(entrada.next());
        System.out.println("Nacionalidad: ");
        autor.setNacionalidad(entrada.next());
        System.out.println("Seudonimo: ");
        autor.setSeudonimo(entrada.next());
    }

    public void mostrarDatosAutor(){
        System.out.println("Datos del autor");
        System.out.println("RFC: "+autor.getRfc());
        System.out.println("Nombre: "+autor.getNombre() + " "+autor.getPrimerApellido());
        System.out.println("Nacionalidad: "+autor.getNacionalidad());
        System.out.println("Seudonimo: "+autor.getSeudonimo());
    }

    public void capturarDatosUsuario(){
        System.out.println("RFC: ");
        usuario.setRfc(entrada.next());
        System.out.println("Nombre: ");
        usuario.setNombre(entrada.next());
        System.out.println("Primer Apellido: ");
        usuario.setPrimerApellido(entrada.next());
        System.out.println("Numero de credencial: ");
        usuario.setNumeroCredencial(entrada.next());
    }

    public void mostrarDatosUsuario(){
        System.out.println("Datos del usuario");
        System.out.println("RFC: "+usuario.getRfc());
        System.out.println("Nombre: "+usuario.getNombre() + " "+usuario.getPrimerApellido());
        System.out.println("Numero de credencial: "+usuario.getNumeroCredencial());
        System.out.println("Activo: "+usuario.isActivo());
    }

    public static void main(String[] args) {
        System.out.println("CONTRERAS MARTINEZ BRYAN DANIEL");
        TestHerencia test = new TestHerencia();
        Scanner menu = new Scanner(System.in);
        int opcion;
        do{
            System.out.println(" SISTEMA DE BIBLIOTECA ");
            System.out.println("1. Registrar Bibliotecario");
            System.out.println("2. Registrar Autor");
            System.out.println("3. Registrar Usuario");
            System.out.println("0. Salir");
            System.out.println("Seleccione una opcion: ");
            opcion = menu.nextInt();

            switch(opcion){
                case 1: test.capturarDatosBibliotecario();
                        test.mostrarDatosBibliotecario();
                        break;
                case 2: test.capturarDatosAutor();
                        test.mostrarDatosAutor();
                        break;
                case 3: test.capturarDatosUsuario();
                        test.mostrarDatosUsuario();
                        break;
                case 0: System.out.println("Saliendo del sistema..");
                break;
                default: System.out.println("Opcion no valida");
            }
        } while(opcion != 0);
        System.out.println("CONTRERAS MARTINEZ BRYAN DANIEL");
    }
}

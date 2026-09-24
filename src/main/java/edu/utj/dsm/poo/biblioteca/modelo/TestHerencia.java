/*
CONTRERAS MARTINEZ BRYAN DANIEL
 */
package edu.utj.dsm.poo.biblioteca.modelo;

/**
 *
 * @author Tazit
 */
import java.util.Scanner;
public class TestHerencia {
    private Scanner entrada = new Scanner(System.in);
  private  Bibliotecario biblio =new Bibliotecario();
   private Autor autor =new Autor();
  private  Usuario usuario =new Usuario();
  
  public void capturarDatosBibliotecario(){
      System.out.println("RFC: ");
      biblio.setRfc(entrada.next());
      System.out.println("Nombre: ");
      biblio.setNombre(entrada.next());
      System.out.println("Primer Apellido: ");
      biblio.setPrimerApe(entrada.next());
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
      autor.setPrimerApe(entrada.next());
        System.out.println("Genero: ");
        autor.setGenero(entrada.next());
        System.out.println("Nacionalidad: ");
        autor.setNacionalidad(entrada.next());
  }
    
  
    public void mostrarDatosAutor(){
        System.out.println("Datos del autor");
        System.out.println("RFC: "+autor.getRfc());
        System.out.println("Nombre: "+autor.getNombre() + " "+autor.getPrimerApellido());
        System.out.println("Genero: "+autor.getGenero());
        System.out.println("Nacionalidad: "+autor.getNacionalidad());
    }
    public void capturarDatosUsuario(){
      System.out.println("RFC: ");
      usuario.setRfc(entrada.next());
      System.out.println("Nombre: ");
      usuario.setNombre(entrada.next());
      System.out.println("Primer Apellido: ");
      usuario.setPrimerApe(entrada.next());
        System.out.println("Direccion: ");
        usuario.setDireccion(entrada.next());
        System.out.println("Telefono: ");
        usuario.setTelefono(entrada.next());
  }
  
    public void mostrarDatosUsuario(){
        System.out.println("Datos del usuario");
        System.out.println("RFC: "+usuario.getRfc());
        System.out.println("Nombre: "+usuario.getNombre() + " "+usuario.getPrimerApellido());
        System.out.println("Direccion: "+usuario.getDireccion());
        System.out.println("Telefono: "+usuario.getTelefono());
    }
    public static void main(String[] args) {
        System.out.println("CONTRERAS MARTINEZ BRYAN DANIEL");
        // Aqui crear un menu ciclado para seleccionar un tipo de persona
        TestHerencia test=new TestHerencia();
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

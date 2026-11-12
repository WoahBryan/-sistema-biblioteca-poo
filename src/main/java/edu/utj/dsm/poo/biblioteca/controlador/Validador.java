package edu.utj.dsm.poo.biblioteca.controlador;

public class Validador {

    // Verifica si un campo está vacío
    public static boolean esVacio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    // Verifica si contiene solo letras (y espacios/acentos)
    public static boolean esSoloLetras(String texto) {
        if (esVacio(texto)) return false;
        return texto.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$");
    }

    // Verifica si es un número entero
    public static boolean esNumeroEntero(String texto) {
        if (esVacio(texto)) return false;
        return texto.matches("^\\d+$");
    }

    // Verifica si es un número decimal
    public static boolean esDecimal(String texto) {
        if (esVacio(texto)) return false;
        return texto.matches("^\\d+(\\.\\d+)?$");
    }
}
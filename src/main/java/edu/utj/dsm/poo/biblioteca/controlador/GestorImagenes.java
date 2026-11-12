package edu.utj.dsm.poo.biblioteca.controlador; 

import javafx.stage.FileChooser;
import javafx.stage.Window;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class GestorImagenes {

    // 1. Selecciona la imagen (esto ya lo teníamos)
    public static File seleccionarImagen(Window ventana) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg")
        );
        return fileChooser.showOpenDialog(ventana);
    }

    // 2. NUEVO: Copia la imagen a una carpeta del proyecto y devuelve la nueva ruta
    public static String guardarImagenEnProyecto(File archivoOrigen) {
        if (archivoOrigen == null) return null;

        // Creamos una carpeta llamada "imagenes_sistema" en la raíz del proyecto
        String directorioDestino = System.getProperty("user.dir") + File.separator + "imagenes_sistema";
        File dir = new File(directorioDestino);
        if (!dir.exists()) {
            dir.mkdirs(); // Si la carpeta no existe, la crea
        }

        // Le ponemos un nombre único para que no se sobreescriban si se llaman igual
        String extension = archivoOrigen.getName().substring(archivoOrigen.getName().lastIndexOf("."));
        String nuevoNombre = UUID.randomUUID().toString() + extension;
        
        Path rutaDestino = Paths.get(directorioDestino, nuevoNombre);

        try {
            // Copiamos el archivo
            Files.copy(archivoOrigen.toPath(), rutaDestino, StandardCopyOption.REPLACE_EXISTING);
            // Devolvemos la ruta donde quedó guardado para meterla a la Base de Datos
            return rutaDestino.toString();
        } catch (IOException e) {
            System.out.println("Error al copiar la imagen: " + e.getMessage());
            return null;
        }
    }

    // 3. NUEVO: Elimina el archivo físico de la carpeta (para cuando borres el registro)
    public static void eliminarImagenFisica(String rutaImagen) {
        if (rutaImagen != null && !rutaImagen.isEmpty()) {
            try {
                Files.deleteIfExists(Paths.get(rutaImagen));
            } catch (IOException e) {
                System.out.println("Error al borrar la imagen física: " + e.getMessage());
            }
        }
    }
}
package edu.utj.dsm.poo.biblioteca.persistencia;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javafx.scene.image.Image;

public class ImagenUtil {

    private static final String CARPETA_BASE = "imagenes";

    public static String copiar(File origen, String subcarpeta) throws IOException {
        Path carpeta = Paths.get(CARPETA_BASE, subcarpeta);
        Files.createDirectories(carpeta);
        String nombre = System.currentTimeMillis() + "_" + origen.getName();
        Path destino = carpeta.resolve(nombre);
        Files.copy(origen.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);
        return destino.toString();
    }

    public static void borrar(String ruta) {
        if (ruta == null || ruta.isEmpty()) return;
        try {
            Files.deleteIfExists(Paths.get(ruta));
        } catch (IOException e) {
            System.err.println("No se pudo borrar la imagen: " + e.getMessage());
        }
    }

    public static Image cargar(String ruta) {
        if (ruta == null || ruta.isEmpty()) return null;
        File f = new File(ruta);
        return f.exists() ? new Image(f.toURI().toString()) : null;
    }
}
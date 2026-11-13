// CONTRERAS MARTINEZ BRYAN DANIEL
package edu.utj.dsm.poo.biblioteca.persistencia;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.image.Image;

public class ImagenUtil {

    // Ruta automática hacia la carpeta "Documentos" del sistema operativo
    private static final String RUTA_DOCUMENTOS = System.getProperty("user.home") + File.separator + "Documents";
    private static final String CARPETA_APP = "SistemaBiblioteca";
    private static final Path CARPETA_BASE = Paths.get(RUTA_DOCUMENTOS, CARPETA_APP);
    private static final Path ARCHIVO_LOG = CARPETA_BASE.resolve("registro_directorios.log");

    public static void inicializarCarpetas() {
        try {
            if (!Files.exists(CARPETA_BASE)) {
                Files.createDirectories(CARPETA_BASE);
                escribirLog("ÉXITO - Se creó la carpeta principal de la aplicación: " + CARPETA_BASE.toString());
            }
            
            Path carpetaImagenes = CARPETA_BASE.resolve("imagenes");
            if (!Files.exists(carpetaImagenes)) {
                Files.createDirectories(carpetaImagenes);
                escribirLog("ÉXITO - Se creó la subcarpeta de imágenes: " + carpetaImagenes.toString());
            }
        } catch (IOException e) {
            escribirLog("ERROR - Fallo al inicializar carpetas: " + e.getMessage());
        }
    }

    private static void escribirLog(String mensaje) {
        try (FileWriter fw = new FileWriter(ARCHIVO_LOG.toFile(), true);
             PrintWriter pw = new PrintWriter(fw)) {
            LocalDateTime ahora = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            pw.println("[" + ahora.format(formatter) + "] " + mensaje);
        } catch (IOException e) {
            System.err.println("Error al escribir el log: " + e.getMessage());
        }
    }

    public static String copiar(File origen, String subcarpeta) throws IOException {
        Path carpeta = CARPETA_BASE.resolve("imagenes").resolve(subcarpeta);
        if (!Files.exists(carpeta)) {
            Files.createDirectories(carpeta);
            escribirLog("ÉXITO - Se creó la subcarpeta específica: " + carpeta.toString());
        }
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
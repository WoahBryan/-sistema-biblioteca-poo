// CONTRERAS MARTINEZ BRYAN DANIEL
package edu.utj.dsm.poo.biblioteca;

import edu.utj.dsm.poo.biblioteca.persistencia.ImagenUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Valida y crea las carpetas/log en Documentos al arrancar el sistema
        ImagenUtil.inicializarCarpetas();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Acceso al Sistema - Biblioteca");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
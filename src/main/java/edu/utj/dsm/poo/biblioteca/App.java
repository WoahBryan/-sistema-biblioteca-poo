// CONTRERAS MARTINEZ BRYAN DANIEL
package edu.utj.dsm.poo.biblioteca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
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
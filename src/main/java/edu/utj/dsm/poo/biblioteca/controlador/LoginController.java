// CONTRERAS MARTINEZ BRYAN DANIEL
package edu.utj.dsm.poo.biblioteca.controlador;

import edu.utj.dsm.poo.biblioteca.modelo.Cuenta;
import edu.utj.dsm.poo.biblioteca.persistencia.AuthDAO;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController {

    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtContrasena;
    @FXML private Label lblMensaje;
    
    private AuthDAO authDAO = new AuthDAO();
    public static Cuenta usuarioSesionActual;

    @FXML
    private void accionIngresar(ActionEvent event) {
        String usuario = txtUsuario.getText().trim();
        String pass = txtContrasena.getText().trim();

        if (usuario.isEmpty() || pass.isEmpty()) {
            lblMensaje.setText("Por favor llena ambos campos.");
            return;
        }

        Cuenta cuenta = authDAO.autenticar(usuario, pass);

        if (cuenta != null) {
            usuarioSesionActual = cuenta;
            cargarPantallaPrincipal();
        } else {
            lblMensaje.setText("Credenciales incorrectas o cuenta inactiva.");
        }
    }

    private void cargarPantallaPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AutorView.fxml"));
            Parent root = loader.load();
            
            Stage stage = (Stage) txtUsuario.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Sistema de Biblioteca - Usuario: " + usuarioSesionActual.getNombre() + " (" + usuarioSesionActual.getNombreRol() + ")");
            stage.show();
        } catch (IOException e) {
            System.err.println("Error al cargar la pantalla principal: " + e.getMessage());
        }
    }

    @FXML
    private void accionSalir(ActionEvent event) {
        System.exit(0);
    }
}
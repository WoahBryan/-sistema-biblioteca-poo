// CONTRERAS MARTINEZ BRYAN DANIEL
package edu.utj.dsm.poo.biblioteca.controlador;

import edu.utj.dsm.poo.biblioteca.modelo.Revista;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

public class RevistaViewController {

    @FXML private TextField txtId;
    @FXML private TextField txtTitulo;
    @FXML private TextField txtEditorial;
    @FXML private TextField txtIssn;
    @FXML private TextField txtNumero;
    @FXML private TextField txtPeriodicidad;

    @FXML private Label lblErrorTitulo;
    @FXML private Label lblErrorEditorial;
    @FXML private Label lblErrorIssn;
    @FXML private Label lblErrorNumero;
    @FXML private Label lblErrorPeriodicidad;

    @FXML private Button btnNuevo;
    @FXML private Button btnGuardar;
    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;
    @FXML private Button btnLimpiar;
    @FXML private Button btnValidar;

    @FXML private TableView<Revista> tablaRevistas;
    @FXML private TableColumn<Revista, Integer> colId;
    @FXML private TableColumn<Revista, String> colTitulo;
    @FXML private TableColumn<Revista, String> colEditorial;
    @FXML private TableColumn<Revista, String> colIssn;
    @FXML private TableColumn<Revista, Integer> colNumero;
    @FXML private TableColumn<Revista, String> colPeriodicidad;

    private ObservableList<Revista> listaRevistas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
        colIssn.setCellValueFactory(new PropertyValueFactory<>("issn"));
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colPeriodicidad.setCellValueFactory(new PropertyValueFactory<>("periodicidad"));
        tablaRevistas.setItems(listaRevistas);

        configurarEstadoInicialBotones();

        txtTitulo.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtEditorial.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtIssn.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtNumero.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtPeriodicidad.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());

        tablaRevistas.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                btnEditar.setDisable(false);
                btnEliminar.setDisable(false);
            }
        });
    }

    private void validarCamposEnTiempoReal() {
        boolean valido = true;

        if (Validador.esVacio(txtTitulo.getText())) {
            lblErrorTitulo.setText("El título es obligatorio.");
            valido = false;
        } else {
            lblErrorTitulo.setText("");
        }

        if (Validador.esVacio(txtEditorial.getText())) {
            lblErrorEditorial.setText("La editorial es obligatoria.");
            valido = false;
        } else {
            lblErrorEditorial.setText("");
        }

        if (Validador.esVacio(txtIssn.getText())) {
            lblErrorIssn.setText("El ISSN es obligatorio.");
            valido = false;
        } else {
            lblErrorIssn.setText("");
        }

        if (Validador.esVacio(txtNumero.getText())) {
            lblErrorNumero.setText("El número es obligatorio.");
            valido = false;
        } else if (!Validador.esNumeroEntero(txtNumero.getText())) {
            lblErrorNumero.setText("Solo números enteros.");
            valido = false;
        } else {
            lblErrorNumero.setText("");
        }

        if (Validador.esVacio(txtPeriodicidad.getText())) {
            lblErrorPeriodicidad.setText("La periodicidad es obligatoria.");
            valido = false;
        } else {
            lblErrorPeriodicidad.setText("");
        }

        btnGuardar.setDisable(!valido);
    }

    @FXML private void restringirNumeros(KeyEvent event) {
        if (!event.getCharacter().matches("[0-9]")) event.consume();
    }

    @FXML
    private void accionValidar(ActionEvent event) {
        validarCamposEnTiempoReal();
        boolean correcto = !btnGuardar.isDisabled();
        Alert alert = new Alert(correcto ? Alert.AlertType.INFORMATION : Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(correcto 
            ? "¡Todos los datos de la revista cumplen las validaciones!" 
            : "Por favor corrige los errores en el formulario.");
        alert.showAndWait();
    }

    @FXML
    private void accionLimpiar(ActionEvent event) {
        txtId.clear();
        txtTitulo.clear();
        txtEditorial.clear();
        txtIssn.clear();
        txtNumero.clear();
        txtPeriodicidad.clear();

        lblErrorTitulo.setText("");
        lblErrorEditorial.setText("");
        lblErrorIssn.setText("");
        lblErrorNumero.setText("");
        lblErrorPeriodicidad.setText("");

        configurarEstadoInicialBotones();
    }

    private void configurarEstadoInicialBotones() {
        btnNuevo.setDisable(false);
        btnValidar.setDisable(false);
        btnLimpiar.setDisable(false);
        btnGuardar.setDisable(true);
        btnEditar.setDisable(true);
        btnEliminar.setDisable(true);
    }
}
// CONTRERAS MARTINEZ BRYAN DANIEL
package edu.utj.dsm.poo.biblioteca.controlador;

import edu.utj.dsm.poo.biblioteca.modelo.Editorial;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

public class EditorialViewController {

    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private TextField txtPais;

    @FXML private Label lblErrorNombre;
    @FXML private Label lblErrorPais;

    @FXML private Button btnNuevo;
    @FXML private Button btnGuardar;
    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;
    @FXML private Button btnLimpiar;
    @FXML private Button btnValidar;

    @FXML private TableView<Editorial> tablaEditoriales;
    @FXML private TableColumn<Editorial, Integer> colId;
    @FXML private TableColumn<Editorial, String> colNombre;
    @FXML private TableColumn<Editorial, String> colPais;

    private ObservableList<Editorial> listaEditoriales = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPais.setCellValueFactory(new PropertyValueFactory<>("pais"));
        tablaEditoriales.setItems(listaEditoriales);

        configurarEstadoInicialBotones();

        txtNombre.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtPais.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());

        tablaEditoriales.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                btnEditar.setDisable(false);
                btnEliminar.setDisable(false);
            }
        });
    }

    private void validarCamposEnTiempoReal() {
        boolean valido = true;

        if (Validador.esVacio(txtNombre.getText())) {
            lblErrorNombre.setText("El nombre es obligatorio.");
            valido = false;
        } else {
            lblErrorNombre.setText("");
        }

        if (Validador.esVacio(txtPais.getText())) {
            lblErrorPais.setText("El país es obligatorio.");
            valido = false;
        } else if (!Validador.esSoloLetras(txtPais.getText())) {
            lblErrorPais.setText("Solo letras permitidas.");
            valido = false;
        } else {
            lblErrorPais.setText("");
        }

        btnGuardar.setDisable(!valido);
    }

    @FXML private void restringirLetras(KeyEvent event) {
        if (!event.getCharacter().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]")) event.consume();
    }

    @FXML
    private void accionValidar(ActionEvent event) {
        validarCamposEnTiempoReal();
        boolean correcto = !btnGuardar.isDisabled();
        Alert alert = new Alert(correcto ? Alert.AlertType.INFORMATION : Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(correcto 
            ? "¡Todos los datos de la editorial cumplen las validaciones!" 
            : "Por favor corrige los errores en el formulario.");
        alert.showAndWait();
    }

    @FXML
    private void accionLimpiar(ActionEvent event) {
        txtId.clear();
        txtNombre.clear();
        txtPais.clear();

        lblErrorNombre.setText("");
        lblErrorPais.setText("");

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
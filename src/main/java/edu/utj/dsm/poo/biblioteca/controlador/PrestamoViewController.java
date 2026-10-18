// CONTRERAS MARTINEZ BRYAN DANIEL
package edu.utj.dsm.poo.biblioteca.controlador;

import edu.utj.dsm.poo.biblioteca.modelo.Prestamo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrestamoViewController {

    @FXML private TextField txtId;
    @FXML private TextField txtUsuario;
    @FXML private TextField txtLibro;
    @FXML private TextField txtFechaPrestamo;
    @FXML private TextField txtFechaDevolucion;

    @FXML private Label lblErrorUsuario;
    @FXML private Label lblErrorLibro;
    @FXML private Label lblErrorFechaPrestamo;
    @FXML private Label lblErrorFechaDevolucion;

    @FXML private Button btnNuevo;
    @FXML private Button btnGuardar;
    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;
    @FXML private Button btnLimpiar;
    @FXML private Button btnValidar;

    @FXML private TableView<Prestamo> tablaPrestamos;
    @FXML private TableColumn<Prestamo, Integer> colId;
    @FXML private TableColumn<Prestamo, String> colUsuario;
    @FXML private TableColumn<Prestamo, String> colLibro;
    @FXML private TableColumn<Prestamo, String> colFechaPrestamo;
    @FXML private TableColumn<Prestamo, String> colFechaDevolucion;

    private ObservableList<Prestamo> listaPrestamos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        colLibro.setCellValueFactory(new PropertyValueFactory<>("libro"));
        colFechaPrestamo.setCellValueFactory(new PropertyValueFactory<>("fechaPrestamo"));
        colFechaDevolucion.setCellValueFactory(new PropertyValueFactory<>("fechaDevolucion"));
        tablaPrestamos.setItems(listaPrestamos);

        configurarEstadoInicialBotones();

        txtUsuario.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtLibro.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtFechaPrestamo.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtFechaDevolucion.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());

        tablaPrestamos.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                btnEditar.setDisable(false);
                btnEliminar.setDisable(false);
            }
        });
    }

    private void validarCamposEnTiempoReal() {
        boolean valido = true;

        if (Validador.esVacio(txtUsuario.getText())) {
            lblErrorUsuario.setText("El usuario es obligatorio.");
            valido = false;
        } else {
            lblErrorUsuario.setText("");
        }

        if (Validador.esVacio(txtLibro.getText())) {
            lblErrorLibro.setText("El libro es obligatorio.");
            valido = false;
        } else {
            lblErrorLibro.setText("");
        }

        if (Validador.esVacio(txtFechaPrestamo.getText())) {
            lblErrorFechaPrestamo.setText("La fecha es obligatoria.");
            valido = false;
        } else {
            lblErrorFechaPrestamo.setText("");
        }

        if (Validador.esVacio(txtFechaDevolucion.getText())) {
            lblErrorFechaDevolucion.setText("La fecha de devolución es obligatoria.");
            valido = false;
        } else {
            lblErrorFechaDevolucion.setText("");
        }

        btnGuardar.setDisable(!valido);
    }

    @FXML
    private void accionValidar(ActionEvent event) {
        validarCamposEnTiempoReal();
        boolean correcto = !btnGuardar.isDisabled();
        Alert alert = new Alert(correcto ? Alert.AlertType.INFORMATION : Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(correcto 
            ? "¡Todos los datos del préstamo cumplen las validaciones!" 
            : "Por favor corrige los errores en el formulario.");
        alert.showAndWait();
    }

    @FXML
    private void accionLimpiar(ActionEvent event) {
        txtId.clear();
        txtUsuario.clear();
        txtLibro.clear();
        txtFechaPrestamo.clear();
        txtFechaDevolucion.clear();

        lblErrorUsuario.setText("");
        lblErrorLibro.setText("");
        lblErrorFechaPrestamo.setText("");
        lblErrorFechaDevolucion.setText("");

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
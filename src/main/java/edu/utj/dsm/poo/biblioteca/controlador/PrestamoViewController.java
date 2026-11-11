// CONTRERAS MARTINEZ BRYAN DANIEL
package edu.utj.dsm.poo.biblioteca.controlador;

import edu.utj.dsm.poo.biblioteca.modelo.Prestamo;
import edu.utj.dsm.poo.biblioteca.persistencia.PrestamoDAO;
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
    private PrestamoDAO prestamoDAO = new PrestamoDAO();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        colLibro.setCellValueFactory(new PropertyValueFactory<>("libro"));
        colFechaPrestamo.setCellValueFactory(new PropertyValueFactory<>("fechaPrestamo"));
        colFechaDevolucion.setCellValueFactory(new PropertyValueFactory<>("fechaDevolucion"));
        
        cargarDatosTabla();
        configurarEstadoInicialBotones();

        txtUsuario.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtLibro.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtFechaPrestamo.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtFechaDevolucion.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());

        tablaPrestamos.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtId.setText(String.valueOf(newSel.getId()));
                txtUsuario.setText(newSel.getUsuario());
                txtLibro.setText(newSel.getLibro());
                txtFechaPrestamo.setText(newSel.getFechaPrestamo());
                txtFechaDevolucion.setText(newSel.getFechaDevolucion());
                
                btnEditar.setDisable(false);
                btnEliminar.setDisable(false);
                btnGuardar.setDisable(true);
            }
        });
    }

    private void cargarDatosTabla() {
        listaPrestamos.clear();
        listaPrestamos.addAll(prestamoDAO.obtenerTodos());
        tablaPrestamos.setItems(listaPrestamos);
    }

    @FXML
    private void accionGuardar(ActionEvent event) {
        Prestamo nuevo = new Prestamo();
        nuevo.setUsuario(txtUsuario.getText());
        nuevo.setLibro(txtLibro.getText());
        nuevo.setFechaPrestamo(txtFechaPrestamo.getText());
        nuevo.setFechaDevolucion(txtFechaDevolucion.getText());

        if (prestamoDAO.insertar(nuevo)) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Préstamo guardado correctamente.");
            cargarDatosTabla();
            accionLimpiar(null);
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo guardar el préstamo.");
        }
    }

    @FXML
    private void accionEditar(ActionEvent event) {
        Prestamo seleccionado = tablaPrestamos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            seleccionado.setUsuario(txtUsuario.getText());
            seleccionado.setLibro(txtLibro.getText());
            seleccionado.setFechaPrestamo(txtFechaPrestamo.getText());
            seleccionado.setFechaDevolucion(txtFechaDevolucion.getText());

            if (prestamoDAO.actualizar(seleccionado)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Préstamo actualizado correctamente.");
                cargarDatosTabla();
                accionLimpiar(null);
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo actualizar el préstamo.");
            }
        }
    }

    @FXML
    private void accionEliminar(ActionEvent event) {
        Prestamo seleccionado = tablaPrestamos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            if (prestamoDAO.eliminar(seleccionado.getId())) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Préstamo eliminado correctamente.");
                cargarDatosTabla();
                accionLimpiar(null);
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo eliminar el préstamo.");
            }
        }
    }

    private void validarCamposEnTiempoReal() {
        boolean valido = true;

        if (txtUsuario.getText() == null || txtUsuario.getText().trim().isEmpty()) {
            lblErrorUsuario.setText("El usuario es obligatorio.");
            valido = false;
        } else {
            lblErrorUsuario.setText("");
        }

        if (txtLibro.getText() == null || txtLibro.getText().trim().isEmpty()) {
            lblErrorLibro.setText("El libro es obligatorio.");
            valido = false;
        } else {
            lblErrorLibro.setText("");
        }

        if (txtFechaPrestamo.getText() == null || txtFechaPrestamo.getText().trim().isEmpty()) {
            lblErrorFechaPrestamo.setText("La fecha es obligatoria.");
            valido = false;
        } else {
            lblErrorFechaPrestamo.setText("");
        }

        if (txtFechaDevolucion.getText() == null || txtFechaDevolucion.getText().trim().isEmpty()) {
            lblErrorFechaDevolucion.setText("La fecha de devolución es obligatoria.");
            valido = false;
        } else {
            lblErrorFechaDevolucion.setText("");
        }

        btnGuardar.setDisable(!valido || !txtId.getText().isEmpty());
    }

    @FXML
    private void accionValidar(ActionEvent event) {
        validarCamposEnTiempoReal();
        boolean correcto = !btnGuardar.isDisabled();
        mostrarAlerta(correcto ? Alert.AlertType.INFORMATION : Alert.AlertType.WARNING, "Validación", 
            correcto ? "¡Todos los datos del préstamo cumplen las validaciones!" : "Por favor corrige los errores en el formulario.");
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
        tablaPrestamos.getSelectionModel().clearSelection();
    }

    private void configurarEstadoInicialBotones() {
        btnNuevo.setDisable(false);
        btnValidar.setDisable(false);
        btnLimpiar.setDisable(false);
        btnGuardar.setDisable(true);
        btnEditar.setDisable(true);
        btnEliminar.setDisable(true);
    }
    
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(null);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
// CONTRERAS MARTINEZ BRYAN DANIEL
package edu.utj.dsm.poo.biblioteca.controlador;

import edu.utj.dsm.poo.biblioteca.modelo.Editorial;
import edu.utj.dsm.poo.biblioteca.persistencia.EditorialDAO;
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
    private EditorialDAO editorialDAO = new EditorialDAO();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPais.setCellValueFactory(new PropertyValueFactory<>("pais"));
        
        cargarDatosTabla();

        configurarEstadoInicialBotones();

        txtNombre.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtPais.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());

        tablaEditoriales.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtId.setText(String.valueOf(newSel.getId()));
                txtNombre.setText(newSel.getNombre());
                txtPais.setText(newSel.getPais());
                
                btnEditar.setDisable(false);
                btnEliminar.setDisable(false);
                btnGuardar.setDisable(true);
            }
        });
    }

    private void cargarDatosTabla() {
        listaEditoriales.clear();
        listaEditoriales.addAll(editorialDAO.obtenerTodos());
        tablaEditoriales.setItems(listaEditoriales);
    }

    @FXML
    private void accionGuardar(ActionEvent event) {
        Editorial nuevaEditorial = new Editorial();
        nuevaEditorial.setNombre(txtNombre.getText());
        nuevaEditorial.setPais(txtPais.getText());

        if (editorialDAO.insertar(nuevaEditorial)) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Editorial guardada correctamente.");
            cargarDatosTabla();
            accionLimpiar(null);
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo guardar la editorial.");
        }
    }

    @FXML
    private void accionEditar(ActionEvent event) {
        Editorial editorialSeleccionada = tablaEditoriales.getSelectionModel().getSelectedItem();
        if (editorialSeleccionada != null) {
            editorialSeleccionada.setNombre(txtNombre.getText());
            editorialSeleccionada.setPais(txtPais.getText());

            if (editorialDAO.actualizar(editorialSeleccionada)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Editorial actualizada correctamente.");
                cargarDatosTabla();
                accionLimpiar(null);
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo actualizar la editorial.");
            }
        }
    }

    @FXML
    private void accionEliminar(ActionEvent event) {
        Editorial editorialSeleccionada = tablaEditoriales.getSelectionModel().getSelectedItem();
        if (editorialSeleccionada != null) {
            if (editorialDAO.eliminar(editorialSeleccionada.getId())) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Editorial eliminada correctamente.");
                cargarDatosTabla();
                accionLimpiar(null);
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo eliminar la editorial.");
            }
        }
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

        btnGuardar.setDisable(!valido || !txtId.getText().isEmpty());
    }

    @FXML private void restringirLetras(KeyEvent event) {
        if (!event.getCharacter().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]")) event.consume();
    }

    @FXML
    private void accionValidar(ActionEvent event) {
        validarCamposEnTiempoReal();
        boolean correcto = txtNombre.getText().length() > 0 && txtPais.getText().length() > 0;
        mostrarAlerta(correcto ? Alert.AlertType.INFORMATION : Alert.AlertType.WARNING, "Validación", correcto ? "¡Todos los datos cumplen las validaciones!" : "Por favor corrige los errores.");
    }

    @FXML
    private void accionLimpiar(ActionEvent event) {
        txtId.clear();
        txtNombre.clear();
        txtPais.clear();

        lblErrorNombre.setText("");
        lblErrorPais.setText("");

        configurarEstadoInicialBotones();
        tablaEditoriales.getSelectionModel().clearSelection();
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
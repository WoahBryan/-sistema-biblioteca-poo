// CONTRERAS MARTINEZ BRYAN DANIEL
package edu.utj.dsm.poo.biblioteca.controlador;

import edu.utj.dsm.poo.biblioteca.modelo.Multa;
import edu.utj.dsm.poo.biblioteca.persistencia.MultaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MultaViewController {

    @FXML private TextField txtId;
    @FXML private TextField txtUsuario;
    @FXML private TextField txtMonto;
    @FXML private TextField txtMotivo;
    @FXML private ComboBox<String> cmbEstado;

    @FXML private Label lblErrorUsuario;
    @FXML private Label lblErrorMonto;
    @FXML private Label lblErrorMotivo;

    @FXML private Button btnNuevo;
    @FXML private Button btnGuardar;
    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;
    @FXML private Button btnLimpiar;
    @FXML private Button btnValidar;

    @FXML private TableView<Multa> tablaMultas;
    @FXML private TableColumn<Multa, Integer> colId;
    @FXML private TableColumn<Multa, String> colUsuario;
    @FXML private TableColumn<Multa, Double> colMonto;
    @FXML private TableColumn<Multa, String> colMotivo;
    @FXML private TableColumn<Multa, String> colEstado;

    private ObservableList<Multa> listaMultas = FXCollections.observableArrayList();
    private MultaDAO multaDAO = new MultaDAO();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        colMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        colMotivo.setCellValueFactory(new PropertyValueFactory<>("motivo"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        
        cmbEstado.getItems().addAll("Pendiente", "Pagada", "Cancelada");
        
        cargarDatosTabla();
        configurarEstadoInicialBotones();

        txtUsuario.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtMonto.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtMotivo.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        cmbEstado.valueProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());

        tablaMultas.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtId.setText(String.valueOf(newSel.getId()));
                txtUsuario.setText(newSel.getUsuario());
                txtMonto.setText(String.valueOf(newSel.getMonto()));
                txtMotivo.setText(newSel.getMotivo());
                cmbEstado.setValue(newSel.getEstado());
                
                btnEditar.setDisable(false);
                btnEliminar.setDisable(false);
                btnGuardar.setDisable(true);
            }
        });
    }

    private void cargarDatosTabla() {
        listaMultas.clear();
        listaMultas.addAll(multaDAO.obtenerTodos());
        tablaMultas.setItems(listaMultas);
    }

    @FXML
    private void accionGuardar(ActionEvent event) {
        Multa nuevaMulta = new Multa();
        nuevaMulta.setUsuario(txtUsuario.getText());
        nuevaMulta.setMonto(Double.parseDouble(txtMonto.getText()));
        nuevaMulta.setMotivo(txtMotivo.getText());
        nuevaMulta.setEstado(cmbEstado.getValue());

        if (multaDAO.insertar(nuevaMulta)) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Multa guardada correctamente.");
            cargarDatosTabla();
            accionLimpiar(null);
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo guardar la multa.");
        }
    }

    @FXML
    private void accionEditar(ActionEvent event) {
        Multa multaSeleccionada = tablaMultas.getSelectionModel().getSelectedItem();
        if (multaSeleccionada != null) {
            multaSeleccionada.setUsuario(txtUsuario.getText());
            multaSeleccionada.setMonto(Double.parseDouble(txtMonto.getText()));
            multaSeleccionada.setMotivo(txtMotivo.getText());
            multaSeleccionada.setEstado(cmbEstado.getValue());

            if (multaDAO.actualizar(multaSeleccionada)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Multa actualizada correctamente.");
                cargarDatosTabla();
                accionLimpiar(null);
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo actualizar la multa.");
            }
        }
    }

    @FXML
    private void accionEliminar(ActionEvent event) {
        Multa multaSeleccionada = tablaMultas.getSelectionModel().getSelectedItem();
        if (multaSeleccionada != null) {
            if (multaDAO.eliminar(multaSeleccionada.getId())) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Multa eliminada correctamente.");
                cargarDatosTabla();
                accionLimpiar(null);
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo eliminar la multa.");
            }
        }
    }

    private void validarCamposEnTiempoReal() {
        boolean valido = true;

        if (Validador.esVacio(txtUsuario.getText())) {
            lblErrorUsuario.setText("El usuario es obligatorio.");
            valido = false;
        } else {
            lblErrorUsuario.setText("");
        }

        if (Validador.esVacio(txtMonto.getText())) {
            lblErrorMonto.setText("El monto es obligatorio.");
            valido = false;
        } else if (!Validador.esDecimal(txtMonto.getText())) {
            lblErrorMonto.setText("Debe ser un número válido.");
            valido = false;
        } else {
            lblErrorMonto.setText("");
        }

        if (Validador.esVacio(txtMotivo.getText())) {
            lblErrorMotivo.setText("El motivo es obligatorio.");
            valido = false;
        } else {
            lblErrorMotivo.setText("");
        }
        
        if (cmbEstado.getValue() == null) {
            valido = false;
        }

        btnGuardar.setDisable(!valido || !txtId.getText().isEmpty());
    }

    @FXML
    private void accionValidar(ActionEvent event) {
        validarCamposEnTiempoReal();
        boolean correcto = !btnGuardar.isDisabled();
        mostrarAlerta(correcto ? Alert.AlertType.INFORMATION : Alert.AlertType.WARNING, "Validación", correcto ? "¡Todos los datos cumplen las validaciones!" : "Por favor corrige los errores.");
    }

    @FXML
    private void accionLimpiar(ActionEvent event) {
        txtId.clear();
        txtUsuario.clear();
        txtMonto.clear();
        txtMotivo.clear();
        cmbEstado.setValue(null);

        lblErrorUsuario.setText("");
        lblErrorMonto.setText("");
        lblErrorMotivo.setText("");

        configurarEstadoInicialBotones();
        tablaMultas.getSelectionModel().clearSelection();
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
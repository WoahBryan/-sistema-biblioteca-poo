// CONTRERAS MARTINEZ BRYAN DANIEL
package edu.utj.dsm.poo.biblioteca.controlador;

import edu.utj.dsm.poo.biblioteca.modelo.Multa;
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

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        colMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        colMotivo.setCellValueFactory(new PropertyValueFactory<>("motivo"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        tablaMultas.setItems(listaMultas);

        cmbEstado.getItems().addAll("Pendiente", "Pagada", "Cancelada");

        configurarEstadoInicialBotones();

        txtUsuario.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtMonto.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtMotivo.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());

        tablaMultas.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
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

        btnGuardar.setDisable(!valido);
    }

    @FXML
    private void accionValidar(ActionEvent event) {
        validarCamposEnTiempoReal();
        boolean correcto = !btnGuardar.isDisabled();
        Alert alert = new Alert(correcto ? Alert.AlertType.INFORMATION : Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(correcto 
            ? "¡Todos los datos de la multa cumplen las validaciones!" 
            : "Por favor corrige los errores en el formulario.");
        alert.showAndWait();
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
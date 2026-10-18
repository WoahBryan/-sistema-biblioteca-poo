// CONTRERAS MARTINEZ BRYAN DANIEL
package edu.utj.dsm.poo.biblioteca.controlador;

import edu.utj.dsm.poo.biblioteca.modelo.Bibliotecario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class BibliotecarioViewController {

    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private TextField txtPrimerApellido;
    @FXML private TextField txtTurno;
    @FXML private TextField txtArea;

    @FXML private Label lblErrorNombre;
    @FXML private Label lblErrorApellido;
    @FXML private Label lblErrorTurno;
    @FXML private Label lblErrorArea;

    @FXML private ImageView imgFoto;

    @FXML private Button btnNuevo;
    @FXML private Button btnGuardar;
    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;
    @FXML private Button btnLimpiar;
    @FXML private Button btnValidar;

    @FXML private TableView<Bibliotecario> tablaBibliotecarios;
    @FXML private TableColumn<Bibliotecario, Integer> colId;
    @FXML private TableColumn<Bibliotecario, String> colNombre;
    @FXML private TableColumn<Bibliotecario, String> colApellido;
    @FXML private TableColumn<Bibliotecario, String> colTurno;
    @FXML private TableColumn<Bibliotecario, String> colArea;

    private ObservableList<Bibliotecario> listaBibliotecarios = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("primerApellido"));
        colTurno.setCellValueFactory(new PropertyValueFactory<>("turno"));
        colArea.setCellValueFactory(new PropertyValueFactory<>("area"));
        tablaBibliotecarios.setItems(listaBibliotecarios);

        configurarEstadoInicialBotones();

        txtNombre.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtPrimerApellido.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtTurno.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtArea.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());

        tablaBibliotecarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
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
        } else if (!Validador.esSoloLetras(txtNombre.getText())) {
            lblErrorNombre.setText("Solo letras permitidas.");
            valido = false;
        } else {
            lblErrorNombre.setText("");
        }

        if (Validador.esVacio(txtPrimerApellido.getText())) {
            lblErrorApellido.setText("El apellido es obligatorio.");
            valido = false;
        } else if (!Validador.esSoloLetras(txtPrimerApellido.getText())) {
            lblErrorApellido.setText("Solo letras permitidas.");
            valido = false;
        } else {
            lblErrorApellido.setText("");
        }

        if (Validador.esVacio(txtTurno.getText())) {
            lblErrorTurno.setText("El turno es obligatorio.");
            valido = false;
        } else {
            lblErrorTurno.setText("");
        }

        if (Validador.esVacio(txtArea.getText())) {
            lblErrorArea.setText("El área es obligatoria.");
            valido = false;
        } else {
            lblErrorArea.setText("");
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
            ? "¡Todos los datos del bibliotecario cumplen las validaciones!" 
            : "Por favor corrige los errores en el formulario.");
        alert.showAndWait();
    }

    @FXML
    private void accionLimpiar(ActionEvent event) {
        txtId.clear();
        txtNombre.clear();
        txtPrimerApellido.clear();
        txtTurno.clear();
        txtArea.clear();

        lblErrorNombre.setText("");
        lblErrorApellido.setText("");
        lblErrorTurno.setText("");
        lblErrorArea.setText("");

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
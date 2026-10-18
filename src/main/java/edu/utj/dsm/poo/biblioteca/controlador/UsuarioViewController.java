package edu.utj.dsm.poo.biblioteca.controlador;

import edu.utj.dsm.poo.biblioteca.modelo.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

public class UsuarioViewController {

    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private TextField txtPrimerApellido;
    @FXML private TextField txtNumeroCredencial;
    @FXML private ComboBox<String> cmbEstado;

    // Labels de error
    @FXML private Label lblErrorNombre;
    @FXML private Label lblErrorApellido;
    @FXML private Label lblErrorCredencial;

    // Botones
    @FXML private Button btnNuevo;
    @FXML private Button btnGuardar;
    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;
    @FXML private Button btnLimpiar;
    @FXML private Button btnValidar;

    @FXML private TableView<Usuario> tablaUsuarios;
    @FXML private TableColumn<Usuario, Integer> colId;
    @FXML private TableColumn<Usuario, String> colNombre;
    @FXML private TableColumn<Usuario, String> colApellido;
    @FXML private TableColumn<Usuario, String> colCredencial;
    @FXML private TableColumn<Usuario, String> colEstado;

    private ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configurar tabla
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("primerApellido"));
        colCredencial.setCellValueFactory(new PropertyValueFactory<>("numeroCredencial"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        tablaUsuarios.setItems(listaUsuarios);

        // Opciones del ComboBox
        cmbEstado.getItems().addAll("Activo", "Inactivo", "Suspendido");

        // Estado inicial de los botones
        configurarEstadoInicialBotones();

        // Listeners para validación en tiempo real (textProperty)
        txtNombre.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtPrimerApellido.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtNumeroCredencial.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());

        // Evento para habilitar Editar/Eliminar al seleccionar en tabla
        tablaUsuarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnEditar.setDisable(false);
                btnEliminar.setDisable(false);
            }
        });
    }

    // --- VALIDACIONES EN TIEMPO REAL ---
    private void validarCamposEnTiempoReal() {
        boolean formularioValido = true;

        // Validar Nombre
        if (Validador.esVacio(txtNombre.getText())) {
            lblErrorNombre.setText("El nombre es obligatorio.");
            formularioValido = false;
        } else if (!Validador.esSoloLetras(txtNombre.getText())) {
            lblErrorNombre.setText("Solo se permiten letras.");
            formularioValido = false;
        } else {
            lblErrorNombre.setText("");
        }

        // Validar Apellido
        if (Validador.esVacio(txtPrimerApellido.getText())) {
            lblErrorApellido.setText("El apellido es obligatorio.");
            formularioValido = false;
        } else if (!Validador.esSoloLetras(txtPrimerApellido.getText())) {
            lblErrorApellido.setText("Solo se permiten letras.");
            formularioValido = false;
        } else {
            lblErrorApellido.setText("");
        }

        // Validar Credencial (Solo números)
        if (Validador.esVacio(txtNumeroCredencial.getText())) {
            lblErrorCredencial.setText("La credencial es obligatoria.");
            formularioValido = false;
        } else if (!Validador.esNumeroEntero(txtNumeroCredencial.getText())) {
            lblErrorCredencial.setText("Solo se permiten números.");
            formularioValido = false;
        } else {
            lblErrorCredencial.setText("");
        }

        // Habilitar el botón Guardar solo si todo es válido
        btnGuardar.setDisable(!formularioValido);
    }

    // --- RESTRICCIÓN DE TECLADO (KEY_TYPED) ---
    @FXML
    private void restringirLetras(KeyEvent event) {
        if (!event.getCharacter().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]")) {
            event.consume(); // Bloquea la tecla si no es letra
        }
    }

    @FXML
    private void restringirNumeros(KeyEvent event) {
        if (!event.getCharacter().matches("[0-9]")) {
            event.consume(); // Bloquea la tecla si no es número
        }
    }

    // --- ACCIONES DE BOTONES ---
    @FXML
    private void accionValidar(ActionEvent event) {
        validarCamposEnTiempoReal();
        if (!btnGuardar.isDisabled()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("¡Todos los datos cumplen las validaciones correctamente!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Por favor corrige los errores en el formulario.");
            alert.showAndWait();
        }
    }

    @FXML
    private void accionLimpiar(ActionEvent event) {
        txtId.clear();
        txtNombre.clear();
        txtPrimerApellido.clear();
        txtNumeroCredencial.clear();
        cmbEstado.setValue(null);
        lblErrorNombre.setText("");
        lblErrorApellido.setText("");
        lblErrorCredencial.setText("");
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
// CONTRERAS MARTINEZ BRYAN DANIEL
package edu.utj.dsm.poo.biblioteca.controlador;

import edu.utj.dsm.poo.biblioteca.modelo.Usuario;
import edu.utj.dsm.poo.biblioteca.persistencia.UsuarioDAO;
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

    @FXML private Label lblErrorNombre;
    @FXML private Label lblErrorApellido;
    @FXML private Label lblErrorCredencial;

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
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("primerApellido"));
        colCredencial.setCellValueFactory(new PropertyValueFactory<>("numeroCredencial"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        
        cmbEstado.getItems().addAll("Activo", "Inactivo", "Suspendido");
        
        cargarDatosTabla();
        configurarEstadoInicialBotones();

        txtNombre.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtPrimerApellido.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtNumeroCredencial.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        cmbEstado.valueProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());

        tablaUsuarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtId.setText(String.valueOf(newSelection.getId()));
                txtNombre.setText(newSelection.getNombre());
                txtPrimerApellido.setText(newSelection.getPrimerApellido());
                txtNumeroCredencial.setText(newSelection.getNumeroCredencial());
                cmbEstado.setValue(newSelection.getEstado());
                
                btnEditar.setDisable(false);
                btnEliminar.setDisable(false);
                btnGuardar.setDisable(true);
            }
        });
    }

    private void cargarDatosTabla() {
        listaUsuarios.clear();
        listaUsuarios.addAll(usuarioDAO.obtenerTodos());
        tablaUsuarios.setItems(listaUsuarios);
    }

    @FXML
    private void accionGuardar(ActionEvent event) {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(txtNombre.getText());
        nuevoUsuario.setPrimerApellido(txtPrimerApellido.getText());
        nuevoUsuario.setNumeroCredencial(txtNumeroCredencial.getText());
        nuevoUsuario.setEstado(cmbEstado.getValue());

        if (usuarioDAO.insertar(nuevoUsuario)) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Usuario guardado correctamente.");
            cargarDatosTabla();
            accionLimpiar(null);
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo guardar el usuario.");
        }
    }

    @FXML
    private void accionEditar(ActionEvent event) {
        Usuario usuarioSeleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();
        if (usuarioSeleccionado != null) {
            usuarioSeleccionado.setNombre(txtNombre.getText());
            usuarioSeleccionado.setPrimerApellido(txtPrimerApellido.getText());
            usuarioSeleccionado.setNumeroCredencial(txtNumeroCredencial.getText());
            usuarioSeleccionado.setEstado(cmbEstado.getValue());

            if (usuarioDAO.actualizar(usuarioSeleccionado)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Usuario actualizado correctamente.");
                cargarDatosTabla();
                accionLimpiar(null);
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo actualizar el usuario.");
            }
        }
    }

    @FXML
    private void accionEliminar(ActionEvent event) {
        Usuario usuarioSeleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();
        if (usuarioSeleccionado != null) {
            if (usuarioDAO.eliminar(usuarioSeleccionado.getId())) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Usuario eliminado correctamente.");
                cargarDatosTabla();
                accionLimpiar(null);
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo eliminar el usuario.");
            }
        }
    }

    private void validarCamposEnTiempoReal() {
        boolean valido = true;

        if (Validador.esVacio(txtNombre.getText())) {
            lblErrorNombre.setText("El nombre es obligatorio.");
            valido = false;
        } else if (!Validador.esSoloLetras(txtNombre.getText())) {
            lblErrorNombre.setText("Solo se permiten letras.");
            valido = false;
        } else {
            lblErrorNombre.setText("");
        }

        if (Validador.esVacio(txtPrimerApellido.getText())) {
            lblErrorApellido.setText("El apellido es obligatorio.");
            valido = false;
        } else if (!Validador.esSoloLetras(txtPrimerApellido.getText())) {
            lblErrorApellido.setText("Solo se permiten letras.");
            valido = false;
        } else {
            lblErrorApellido.setText("");
        }

        if (Validador.esVacio(txtNumeroCredencial.getText())) {
            lblErrorCredencial.setText("La credencial es obligatoria.");
            valido = false;
        } else if (!Validador.esNumeroEntero(txtNumeroCredencial.getText())) {
            lblErrorCredencial.setText("Solo se permiten números.");
            valido = false;
        } else {
            lblErrorCredencial.setText("");
        }
        
        if (cmbEstado.getValue() == null) {
            valido = false;
        }

        btnGuardar.setDisable(!valido || !txtId.getText().isEmpty());
    }

    @FXML private void restringirLetras(KeyEvent event) {
        if (!event.getCharacter().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]")) event.consume();
    }

    @FXML private void restringirNumeros(KeyEvent event) {
        if (!event.getCharacter().matches("[0-9]")) event.consume();
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
        txtNombre.clear();
        txtPrimerApellido.clear();
        txtNumeroCredencial.clear();
        cmbEstado.setValue(null);

        lblErrorNombre.setText("");
        lblErrorApellido.setText("");
        lblErrorCredencial.setText("");

        configurarEstadoInicialBotones();
        tablaUsuarios.getSelectionModel().clearSelection();
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
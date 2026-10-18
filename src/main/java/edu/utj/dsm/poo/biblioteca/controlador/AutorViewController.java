// CONTRERAS MARTINEZ BRYAN DANIEL
package edu.utj.dsm.poo.biblioteca.controlador;

import edu.utj.dsm.poo.biblioteca.modelo.Autor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class AutorViewController {

    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private TextField txtPrimerApellido;
    @FXML private TextField txtNacionalidad;
    @FXML private TextField txtSeudonimo;

    @FXML private Label lblErrorNombre;
    @FXML private Label lblErrorApellido;
    @FXML private Label lblErrorNacionalidad;

    @FXML private ImageView imgFoto;

    @FXML private Button btnNuevo;
    @FXML private Button btnGuardar;
    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;
    @FXML private Button btnLimpiar;
    @FXML private Button btnValidar;

    @FXML private TableView<Autor> tablaAutores;
    @FXML private TableColumn<Autor, Integer> colId;
    @FXML private TableColumn<Autor, String> colNombre;
    @FXML private TableColumn<Autor, String> colApellido;
    @FXML private TableColumn<Autor, String> colNacionalidad;
    @FXML private TableColumn<Autor, String> colSeudonimo;

    private ObservableList<Autor> listaAutores = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("primerApellido"));
        colNacionalidad.setCellValueFactory(new PropertyValueFactory<>("nacionalidad"));
        colSeudonimo.setCellValueFactory(new PropertyValueFactory<>("seudonimo"));
        tablaAutores.setItems(listaAutores);

        configurarEstadoInicialBotones();

        txtNombre.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtPrimerApellido.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtNacionalidad.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());

        tablaAutores.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
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

        if (Validador.esVacio(txtNacionalidad.getText())) {
            lblErrorNacionalidad.setText("La nacionalidad es obligatoria.");
            valido = false;
        } else if (!Validador.esSoloLetras(txtNacionalidad.getText())) {
            lblErrorNacionalidad.setText("Solo letras permitidas.");
            valido = false;
        } else {
            lblErrorNacionalidad.setText("");
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
            ? "¡Todos los datos del autor cumplen las validaciones!" 
            : "Por favor corrige los errores en el formulario.");
        alert.showAndWait();
    }

    @FXML
    private void accionLimpiar(ActionEvent event) {
        txtId.clear();
        txtNombre.clear();
        txtPrimerApellido.clear();
        txtNacionalidad.clear();
        txtSeudonimo.clear();

        lblErrorNombre.setText("");
        lblErrorApellido.setText("");
        lblErrorNacionalidad.setText("");

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
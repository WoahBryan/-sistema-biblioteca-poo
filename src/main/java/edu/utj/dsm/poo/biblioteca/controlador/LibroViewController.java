// CONTRERAS MARTINEZ BRYAN DANIEL
package edu.utj.dsm.poo.biblioteca.controlador;

import edu.utj.dsm.poo.biblioteca.modelo.Libro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class LibroViewController {

    @FXML private TextField txtId;
    @FXML private TextField txtTitulo;
    @FXML private TextField txtAutor;
    @FXML private TextField txtEditorial;
    @FXML private TextField txtISBN;
    @FXML private TextField txtPaginas;

    @FXML private Label lblErrorTitulo;
    @FXML private Label lblErrorAutor;
    @FXML private Label lblErrorEditorial;
    @FXML private Label lblErrorISBN;
    @FXML private Label lblErrorPaginas;

    @FXML private ImageView imgPortada;
    @FXML private ImageView imgContraportada;
    @FXML private ImageView imgLomo;

    @FXML private Button btnNuevo;
    @FXML private Button btnGuardar;
    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;
    @FXML private Button btnLimpiar;
    @FXML private Button btnValidar;

    @FXML private TableView<Libro> tablaLibros;
    @FXML private TableColumn<Libro, Integer> colId;
    @FXML private TableColumn<Libro, String> colTitulo;
    @FXML private TableColumn<Libro, String> colAutor;
    @FXML private TableColumn<Libro, String> colEditorial;
    @FXML private TableColumn<Libro, String> colIsbn;
    @FXML private TableColumn<Libro, Integer> colPaginas;

    private ObservableList<Libro> listaLibros = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        colEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
        colIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colPaginas.setCellValueFactory(new PropertyValueFactory<>("paginas"));
        tablaLibros.setItems(listaLibros);

        configurarEstadoInicialBotones();

        txtTitulo.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtAutor.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtEditorial.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtISBN.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtPaginas.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());

        tablaLibros.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                btnEditar.setDisable(false);
                btnEliminar.setDisable(false);
            }
        });
    }

    private void validarCamposEnTiempoReal() {
        boolean valido = true;

        if (Validador.esVacio(txtTitulo.getText())) {
            lblErrorTitulo.setText("El título es obligatorio.");
            valido = false;
        } else {
            lblErrorTitulo.setText("");
        }

        if (Validador.esVacio(txtAutor.getText())) {
            lblErrorAutor.setText("El autor es obligatorio.");
            valido = false;
        } else if (!Validador.esSoloLetras(txtAutor.getText())) {
            lblErrorAutor.setText("Solo letras permitidas.");
            valido = false;
        } else {
            lblErrorAutor.setText("");
        }

        if (Validador.esVacio(txtEditorial.getText())) {
            lblErrorEditorial.setText("La editorial es obligatoria.");
            valido = false;
        } else {
            lblErrorEditorial.setText("");
        }

        if (Validador.esVacio(txtISBN.getText())) {
            lblErrorISBN.setText("El ISBN es obligatorio.");
            valido = false;
        } else if (!Validador.esNumeroEntero(txtISBN.getText())) {
            lblErrorISBN.setText("Solo números enteros.");
            valido = false;
        } else {
            lblErrorISBN.setText("");
        }

        if (Validador.esVacio(txtPaginas.getText())) {
            lblErrorPaginas.setText("Páginas es obligatorio.");
            valido = false;
        } else if (!Validador.esNumeroEntero(txtPaginas.getText())) {
            lblErrorPaginas.setText("Solo números enteros.");
            valido = false;
        } else {
            lblErrorPaginas.setText("");
        }

        btnGuardar.setDisable(!valido);
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
        boolean formularioCorrecto = !btnGuardar.isDisabled();
        Alert alert = new Alert(formularioCorrecto ? Alert.AlertType.INFORMATION : Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(formularioCorrecto 
            ? "¡Todos los datos del libro cumplen las validaciones!" 
            : "Por favor corrige los errores en el formulario.");
        alert.showAndWait();
    }

    @FXML
    private void accionLimpiar(ActionEvent event) {
        txtId.clear();
        txtTitulo.clear();
        txtAutor.clear();
        txtEditorial.clear();
        txtISBN.clear();
        txtPaginas.clear();

        lblErrorTitulo.setText("");
        lblErrorAutor.setText("");
        lblErrorEditorial.setText("");
        lblErrorISBN.setText("");
        lblErrorPaginas.setText("");

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
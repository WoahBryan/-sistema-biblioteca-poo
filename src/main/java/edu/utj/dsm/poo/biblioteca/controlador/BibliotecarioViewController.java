// CONTRERAS MARTINEZ BRYAN DANIEL
package edu.utj.dsm.poo.biblioteca.controlador;

import edu.utj.dsm.poo.biblioteca.modelo.Bibliotecario;
import edu.utj.dsm.poo.biblioteca.persistencia.BibliotecarioDAO;
import edu.utj.dsm.poo.biblioteca.persistencia.ImagenUtil;
import java.io.File;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;

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
    private BibliotecarioDAO bibliotecarioDAO = new BibliotecarioDAO();

    // Imagen elegida con el FileChooser que todavía no se copia al proyecto
    private File archivoSeleccionado = null;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("primerApellido"));
        colTurno.setCellValueFactory(new PropertyValueFactory<>("turno"));
        colArea.setCellValueFactory(new PropertyValueFactory<>("area"));

        cargarDatosTabla();
        configurarEstadoInicialBotones();

        txtNombre.textProperty().addListener((obs, o, n) -> validarCamposEnTiempoReal());
        txtPrimerApellido.textProperty().addListener((obs, o, n) -> validarCamposEnTiempoReal());
        txtTurno.textProperty().addListener((obs, o, n) -> validarCamposEnTiempoReal());
        txtArea.textProperty().addListener((obs, o, n) -> validarCamposEnTiempoReal());

        tablaBibliotecarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtId.setText(String.valueOf(newSel.getId()));
                txtNombre.setText(newSel.getNombre());
                txtPrimerApellido.setText(newSel.getPrimerApellido());
                txtTurno.setText(newSel.getTurno());
                txtArea.setText(newSel.getArea());

                // Mostrar la imagen guardada del registro
                archivoSeleccionado = null;
                imgFoto.setImage(ImagenUtil.cargar(newSel.getFoto()));

                btnEditar.setDisable(false);
                btnEliminar.setDisable(false);
                btnGuardar.setDisable(true);
            }
        });
    }

    private void cargarDatosTabla() {
        listaBibliotecarios.clear();
        listaBibliotecarios.addAll(bibliotecarioDAO.obtenerTodos());
        tablaBibliotecarios.setItems(listaBibliotecarios);
    }

    @FXML
    private void accionSeleccionarImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen del Bibliotecario");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg"));

        File archivo = fileChooser.showOpenDialog(null);
        if (archivo != null) {
            archivoSeleccionado = archivo;
            imgFoto.setImage(new Image(archivo.toURI().toString()));
        }
    }

    @FXML
    private void accionNuevo(ActionEvent event) {
        accionLimpiar(event);
    }

    @FXML
    private void accionGuardar(ActionEvent event) {
        Bibliotecario nuevo = new Bibliotecario();
        nuevo.setNombre(txtNombre.getText());
        nuevo.setPrimerApellido(txtPrimerApellido.getText());
        nuevo.setTurno(txtTurno.getText());
        nuevo.setArea(txtArea.getText());

        try {
            if (archivoSeleccionado != null) {
                nuevo.setFoto(ImagenUtil.copiar(archivoSeleccionado, "bibliotecarios"));
            }
        } catch (IOException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo copiar la imagen: " + e.getMessage());
            return;
        }

        if (bibliotecarioDAO.insertar(nuevo)) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Bibliotecario guardado correctamente.");
            cargarDatosTabla();
            accionLimpiar(null);
        } else {
            ImagenUtil.borrar(nuevo.getFoto());
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo guardar el bibliotecario.");
        }
    }

    @FXML
    private void accionEditar(ActionEvent event) {
        Bibliotecario seleccionado = tablaBibliotecarios.getSelectionModel().getSelectedItem();
        if (seleccionado == null) return;

        String rutaVieja = seleccionado.getFoto();
        String rutaNueva = rutaVieja;

        try {
            if (archivoSeleccionado != null) {
                rutaNueva = ImagenUtil.copiar(archivoSeleccionado, "bibliotecarios");
            }
        } catch (IOException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo copiar la imagen: " + e.getMessage());
            return;
        }

        seleccionado.setNombre(txtNombre.getText());
        seleccionado.setPrimerApellido(txtPrimerApellido.getText());
        seleccionado.setTurno(txtTurno.getText());
        seleccionado.setArea(txtArea.getText());
        seleccionado.setFoto(rutaNueva);

        if (bibliotecarioDAO.actualizar(seleccionado)) {
            // Si se cambió la imagen, se borra la anterior del disco
            if (archivoSeleccionado != null) {
                ImagenUtil.borrar(rutaVieja);
            }
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Bibliotecario actualizado correctamente.");
            cargarDatosTabla();
            accionLimpiar(null);
        } else {
            if (archivoSeleccionado != null) {
                ImagenUtil.borrar(rutaNueva);
            }
            seleccionado.setFoto(rutaVieja);
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo actualizar el bibliotecario.");
        }
    }

    @FXML
    private void accionEliminar(ActionEvent event) {
        Bibliotecario seleccionado = tablaBibliotecarios.getSelectionModel().getSelectedItem();
        if (seleccionado == null) return;

        String ruta = seleccionado.getFoto();
        if (bibliotecarioDAO.eliminar(seleccionado.getId())) {
            // La imagen se borra solo cuando el registro ya se eliminó de la BD
            ImagenUtil.borrar(ruta);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Bibliotecario eliminado correctamente.");
            cargarDatosTabla();
            accionLimpiar(null);
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo eliminar el bibliotecario.");
        }
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

        btnGuardar.setDisable(!valido || !txtId.getText().isEmpty());
    }

    @FXML
    private void restringirLetras(KeyEvent event) {
        if (!event.getCharacter().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]")) event.consume();
    }

    @FXML
    private void accionValidar(ActionEvent event) {
        validarCamposEnTiempoReal();
        boolean correcto = !btnGuardar.isDisabled();
        mostrarAlerta(correcto ? Alert.AlertType.INFORMATION : Alert.AlertType.WARNING, "Validación",
                correcto ? "¡Todos los datos cumplen las validaciones!" : "Por favor corrige los errores.");
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

        imgFoto.setImage(null);
        archivoSeleccionado = null;
        configurarEstadoInicialBotones();
        tablaBibliotecarios.getSelectionModel().clearSelection();
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
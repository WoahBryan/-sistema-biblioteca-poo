// CONTRERAS MARTINEZ BRYAN DANIEL
package edu.utj.dsm.poo.biblioteca.controlador;

import edu.utj.dsm.poo.biblioteca.modelo.Autor;
import edu.utj.dsm.poo.biblioteca.persistencia.AutorDAO;
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
    private AutorDAO autorDAO = new AutorDAO();

    // Imagen elegida con el FileChooser que todavía no se copia al proyecto
    private File archivoSeleccionado = null;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("primerApellido"));
        colNacionalidad.setCellValueFactory(new PropertyValueFactory<>("nacionalidad"));
        colSeudonimo.setCellValueFactory(new PropertyValueFactory<>("seudonimo"));

        cargarDatosTabla();
        configurarEstadoInicialBotones();

        txtNombre.textProperty().addListener((obs, o, n) -> validarCamposEnTiempoReal());
        txtPrimerApellido.textProperty().addListener((obs, o, n) -> validarCamposEnTiempoReal());
        txtNacionalidad.textProperty().addListener((obs, o, n) -> validarCamposEnTiempoReal());

        tablaAutores.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtId.setText(String.valueOf(newSel.getId()));
                txtNombre.setText(newSel.getNombre());
                txtPrimerApellido.setText(newSel.getPrimerApellido());
                txtNacionalidad.setText(newSel.getNacionalidad());
                txtSeudonimo.setText(newSel.getSeudonimo());

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
        listaAutores.clear();
        listaAutores.addAll(autorDAO.obtenerTodos());
        tablaAutores.setItems(listaAutores);
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
        btnGuardar.setDisable(!valido || !txtId.getText().isEmpty());
    }

    @FXML
    private void restringirLetras(KeyEvent event) {
        if (!event.getCharacter().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]")) event.consume();
    }

    @FXML
    private void accionSeleccionarImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen del Autor");
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
        Autor nuevo = new Autor();
        nuevo.setNombre(txtNombre.getText());
        nuevo.setPrimerApellido(txtPrimerApellido.getText());
        nuevo.setNacionalidad(txtNacionalidad.getText());
        nuevo.setSeudonimo(txtSeudonimo.getText());

        try {
            if (archivoSeleccionado != null) {
                nuevo.setFoto(ImagenUtil.copiar(archivoSeleccionado, "autores"));
            }
        } catch (IOException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo copiar la imagen: " + e.getMessage());
            return;
        }

        if (autorDAO.insertar(nuevo)) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Autor guardado correctamente.");
            cargarDatosTabla();
            accionLimpiar(null);
        } else {
            ImagenUtil.borrar(nuevo.getFoto());
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo guardar el autor en la base de datos.");
        }
    }

    @FXML
    private void accionEditar(ActionEvent event) {
        Autor seleccionado = tablaAutores.getSelectionModel().getSelectedItem();
        if (seleccionado == null) return;

        String rutaVieja = seleccionado.getFoto();
        String rutaNueva = rutaVieja;

        try {
            if (archivoSeleccionado != null) {
                rutaNueva = ImagenUtil.copiar(archivoSeleccionado, "autores");
            }
        } catch (IOException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo copiar la imagen: " + e.getMessage());
            return;
        }

        seleccionado.setNombre(txtNombre.getText());
        seleccionado.setPrimerApellido(txtPrimerApellido.getText());
        seleccionado.setNacionalidad(txtNacionalidad.getText());
        seleccionado.setSeudonimo(txtSeudonimo.getText());
        seleccionado.setFoto(rutaNueva);

        if (autorDAO.actualizar(seleccionado)) {
            // Si se cambió la imagen, se borra la anterior del disco
            if (archivoSeleccionado != null) {
                ImagenUtil.borrar(rutaVieja);
            }
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Autor actualizado correctamente.");
            cargarDatosTabla();
            accionLimpiar(null);
        } else {
            if (archivoSeleccionado != null) {
                ImagenUtil.borrar(rutaNueva);
            }
            seleccionado.setFoto(rutaVieja);
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo actualizar el autor.");
        }
    }

    @FXML
    private void accionEliminar(ActionEvent event) {
        Autor seleccionado = tablaAutores.getSelectionModel().getSelectedItem();
        if (seleccionado == null) return;

        String ruta = seleccionado.getFoto();
        if (autorDAO.eliminar(seleccionado.getId())) {
            // La imagen se borra solo cuando el registro ya se eliminó de la BD
            ImagenUtil.borrar(ruta);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Autor eliminado correctamente.");
            cargarDatosTabla();
            accionLimpiar(null);
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo eliminar el autor.");
        }
    }

    @FXML
    private void accionValidar(ActionEvent event) {
        validarCamposEnTiempoReal();
        boolean correcto = !btnGuardar.isDisabled();
        mostrarAlerta(correcto ? Alert.AlertType.INFORMATION : Alert.AlertType.WARNING, "Validación",
                correcto ? "¡Todos los datos del autor cumplen las validaciones!" : "Por favor corrige los errores en el formulario.");
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
        imgFoto.setImage(null);
        archivoSeleccionado = null;
        configurarEstadoInicialBotones();
        tablaAutores.getSelectionModel().clearSelection();
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
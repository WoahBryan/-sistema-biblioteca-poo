// CONTRERAS MARTINEZ BRYAN DANIEL
package edu.utj.dsm.poo.biblioteca.controlador;

import edu.utj.dsm.poo.biblioteca.modelo.Libro;
import edu.utj.dsm.poo.biblioteca.persistencia.LibroDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import java.io.File;

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
    private LibroDAO libroDAO = new LibroDAO();

    private File filePortada;
    private File fileContraportada;
    private File fileLomo;
    private Libro libroSeleccionado;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        // Las columnas Autor y Editorial no se guardan en esta tabla, se dejan vacías
        colIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colPaginas.setCellValueFactory(new PropertyValueFactory<>("numeroPaginas"));

        cargarDatosTabla();
        configurarEstadoInicialBotones();

        txtTitulo.textProperty().addListener((obs, o, n) -> validarCamposEnTiempoReal());
        txtISBN.textProperty().addListener((obs, o, n) -> validarCamposEnTiempoReal());
        txtPaginas.textProperty().addListener((obs, o, n) -> validarCamposEnTiempoReal());

        // Mostrar datos e imágenes al seleccionar un registro
        tablaLibros.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                libroSeleccionado = newSel;
                mostrarDetallesLibro(newSel);
                btnEditar.setDisable(false);
                btnEliminar.setDisable(false);
                btnGuardar.setDisable(true);
            }
        });
    }

    private void cargarDatosTabla() {
        listaLibros.setAll(libroDAO.listar());
        tablaLibros.setItems(listaLibros);
    }

    private void mostrarDetallesLibro(Libro libro) {
        txtId.setText(String.valueOf(libro.getId()));
        txtTitulo.setText(libro.getTitulo());
        txtISBN.setText(libro.getIsbn());
        txtPaginas.setText(String.valueOf(libro.getNumeroPaginas()));

        filePortada = null;
        fileContraportada = null;
        fileLomo = null;

        cargarImagenVisual(libro.getRutaImagen1(), imgPortada);
        cargarImagenVisual(libro.getRutaImagen2(), imgContraportada);
        cargarImagenVisual(libro.getRutaImagen3(), imgLomo);
    }

    private void cargarImagenVisual(String ruta, ImageView imgView) {
        if (ruta != null && !ruta.isEmpty()) {
            File file = new File(ruta);
            if (file.exists()) {
                imgView.setImage(new Image(file.toURI().toString()));
                return;
            }
        }
        imgView.setImage(null);
    }

    @FXML
    private void seleccionarPortada(ActionEvent event) {
        File f = GestorImagenes.seleccionarImagen(null);
        if (f != null) {
            filePortada = f;
            imgPortada.setImage(new Image(f.toURI().toString()));
        }
    }

    @FXML
    private void seleccionarContraportada(ActionEvent event) {
        File f = GestorImagenes.seleccionarImagen(null);
        if (f != null) {
            fileContraportada = f;
            imgContraportada.setImage(new Image(f.toURI().toString()));
        }
    }

    @FXML
    private void seleccionarLomo(ActionEvent event) {
        File f = GestorImagenes.seleccionarImagen(null);
        if (f != null) {
            fileLomo = f;
            imgLomo.setImage(new Image(f.toURI().toString()));
        }
    }

    @FXML
    private void accionNuevo(ActionEvent event) {
        accionLimpiar(event);
    }

    @FXML
    private void accionGuardar(ActionEvent event) {
        Libro libro = new Libro();
        llenarDatosLibro(libro);
        libro.setAnioPublicacion(2023);
        libro.setDisponible(true);

        // Copiar las imágenes a la carpeta del proyecto y guardar sus rutas
        if (filePortada != null) libro.setRutaImagen1(GestorImagenes.guardarImagenEnProyecto(filePortada));
        if (fileContraportada != null) libro.setRutaImagen2(GestorImagenes.guardarImagenEnProyecto(fileContraportada));
        if (fileLomo != null) libro.setRutaImagen3(GestorImagenes.guardarImagenEnProyecto(fileLomo));

        if (libroDAO.agregar(libro)) {
            mostrarMensaje(Alert.AlertType.INFORMATION, "¡Libro guardado exitosamente!");
            accionLimpiar(null);
            cargarDatosTabla();
        } else {
            // Si falló la BD, no dejamos imágenes huérfanas
            GestorImagenes.eliminarImagenFisica(libro.getRutaImagen1());
            GestorImagenes.eliminarImagenFisica(libro.getRutaImagen2());
            GestorImagenes.eliminarImagenFisica(libro.getRutaImagen3());
            mostrarMensaje(Alert.AlertType.ERROR, "No se pudo guardar el libro en la base de datos.");
        }
    }

    @FXML
    private void accionEditar(ActionEvent event) {
        if (libroSeleccionado == null) return;
        if (!camposValidos()) {
            mostrarMensaje(Alert.AlertType.WARNING, "Revisa los campos obligatorios.");
            return;
        }

        String viejaPortada = libroSeleccionado.getRutaImagen1();
        String viejaContra = libroSeleccionado.getRutaImagen2();
        String viejaLomo = libroSeleccionado.getRutaImagen3();

        llenarDatosLibro(libroSeleccionado);

        // Si se eligieron fotos nuevas, se copian y se guarda la nueva ruta
        if (filePortada != null) libroSeleccionado.setRutaImagen1(GestorImagenes.guardarImagenEnProyecto(filePortada));
        if (fileContraportada != null) libroSeleccionado.setRutaImagen2(GestorImagenes.guardarImagenEnProyecto(fileContraportada));
        if (fileLomo != null) libroSeleccionado.setRutaImagen3(GestorImagenes.guardarImagenEnProyecto(fileLomo));

        if (libroDAO.actualizar(libroSeleccionado, 0)) {
            // Ya actualizado en la BD: se borran del disco las fotos reemplazadas
            if (filePortada != null) GestorImagenes.eliminarImagenFisica(viejaPortada);
            if (fileContraportada != null) GestorImagenes.eliminarImagenFisica(viejaContra);
            if (fileLomo != null) GestorImagenes.eliminarImagenFisica(viejaLomo);
            mostrarMensaje(Alert.AlertType.INFORMATION, "¡Libro actualizado correctamente!");
            accionLimpiar(null);
            cargarDatosTabla();
        } else {
            if (filePortada != null) GestorImagenes.eliminarImagenFisica(libroSeleccionado.getRutaImagen1());
            if (fileContraportada != null) GestorImagenes.eliminarImagenFisica(libroSeleccionado.getRutaImagen2());
            if (fileLomo != null) GestorImagenes.eliminarImagenFisica(libroSeleccionado.getRutaImagen3());
            mostrarMensaje(Alert.AlertType.ERROR, "No se pudo actualizar el libro.");
            cargarDatosTabla();
        }
    }

    @FXML
    private void accionEliminar(ActionEvent event) {
        if (libroSeleccionado == null) return;

        Libro aBorrar = libroSeleccionado;
        if (libroDAO.eliminar(aBorrar)) {
            // Solo si se eliminó de la BD se borran las imágenes físicas
            GestorImagenes.eliminarImagenFisica(aBorrar.getRutaImagen1());
            GestorImagenes.eliminarImagenFisica(aBorrar.getRutaImagen2());
            GestorImagenes.eliminarImagenFisica(aBorrar.getRutaImagen3());
            mostrarMensaje(Alert.AlertType.INFORMATION, "¡Libro eliminado de la BD y fotos borradas!");
            accionLimpiar(null);
            cargarDatosTabla();
        } else {
            mostrarMensaje(Alert.AlertType.ERROR, "No se pudo eliminar el libro.");
        }
    }

    private void llenarDatosLibro(Libro libro) {
        libro.setTitulo(txtTitulo.getText());
        libro.setIsbn(txtISBN.getText());
        libro.setNumeroPaginas(Integer.parseInt(txtPaginas.getText().trim()));
    }

    private boolean camposValidos() {
        boolean valido = true;

        if (txtTitulo.getText() == null || txtTitulo.getText().trim().isEmpty()) {
            lblErrorTitulo.setText("El título es obligatorio.");
            valido = false;
        } else {
            lblErrorTitulo.setText("");
        }

        if (txtISBN.getText() == null || txtISBN.getText().trim().isEmpty()) {
            lblErrorISBN.setText("El ISBN es obligatorio.");
            valido = false;
        } else {
            lblErrorISBN.setText("");
        }

        if (txtPaginas.getText() == null || txtPaginas.getText().trim().isEmpty()) {
            lblErrorPaginas.setText("Las páginas son obligatorias.");
            valido = false;
        } else if (!txtPaginas.getText().trim().matches("\\d+")) {
            lblErrorPaginas.setText("Solo números enteros.");
            valido = false;
        } else {
            lblErrorPaginas.setText("");
        }

        return valido;
    }

    private void validarCamposEnTiempoReal() {
        boolean valido = camposValidos();
        // Guardar solo se activa para un registro nuevo
        if (libroSeleccionado == null) {
            btnGuardar.setDisable(!valido);
        }
    }

    @FXML private void restringirLetras(KeyEvent event) {
        if (!event.getCharacter().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]")) event.consume();
    }

    @FXML private void restringirNumeros(KeyEvent event) {
        if (!event.getCharacter().matches("[0-9]")) event.consume();
    }

    @FXML
    private void accionValidar(ActionEvent event) {
        boolean correcto = camposValidos();
        mostrarMensaje(correcto ? Alert.AlertType.INFORMATION : Alert.AlertType.WARNING,
                correcto ? "¡Todos los datos del libro cumplen las validaciones!" : "Por favor corrige los errores.");
    }

    @FXML
    private void accionLimpiar(ActionEvent event) {
        libroSeleccionado = null;

        txtId.clear(); txtTitulo.clear(); txtAutor.clear(); txtEditorial.clear(); txtISBN.clear(); txtPaginas.clear();
        lblErrorTitulo.setText(""); lblErrorAutor.setText(""); lblErrorEditorial.setText("");
        lblErrorISBN.setText(""); lblErrorPaginas.setText("");

        filePortada = null; fileContraportada = null; fileLomo = null;
        imgPortada.setImage(null); imgContraportada.setImage(null); imgLomo.setImage(null);

        tablaLibros.getSelectionModel().clearSelection();
        configurarEstadoInicialBotones();
    }

    private void configurarEstadoInicialBotones() {
        btnNuevo.setDisable(false); btnValidar.setDisable(false); btnLimpiar.setDisable(false);
        btnGuardar.setDisable(true); btnEditar.setDisable(true); btnEliminar.setDisable(true);
    }

    private void mostrarMensaje(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
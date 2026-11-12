// CONTRERAS MARTINEZ BRYAN DANIEL
package edu.utj.dsm.poo.biblioteca.controlador;

import edu.utj.dsm.poo.biblioteca.modelo.Revista;
import edu.utj.dsm.poo.biblioteca.persistencia.RevistaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

// Importamos el LoginController para saber quién inició sesión
import edu.utj.dsm.poo.biblioteca.controlador.LoginController;

public class RevistaViewController {

    @FXML private TextField txtId;
    @FXML private TextField txtTitulo;
    @FXML private TextField txtEditorial;
    @FXML private TextField txtIssn;
    @FXML private TextField txtNumero;
    @FXML private TextField txtPeriodicidad;

    @FXML private Label lblErrorTitulo;
    @FXML private Label lblErrorEditorial;
    @FXML private Label lblErrorIssn;
    @FXML private Label lblErrorNumero;
    @FXML private Label lblErrorPeriodicidad;

    @FXML private Button btnNuevo;
    @FXML private Button btnGuardar;
    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;
    @FXML private Button btnLimpiar;
    @FXML private Button btnValidar;

    @FXML private TableView<Revista> tablaRevistas;
    @FXML private TableColumn<Revista, Integer> colId;
    @FXML private TableColumn<Revista, String> colTitulo;
    @FXML private TableColumn<Revista, String> colEditorial;
    @FXML private TableColumn<Revista, String> colIssn;
    @FXML private TableColumn<Revista, Integer> colNumero;
    @FXML private TableColumn<Revista, String> colPeriodicidad;

    private ObservableList<Revista> listaRevistas = FXCollections.observableArrayList();
    private RevistaDAO revistaDAO = new RevistaDAO();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
        colIssn.setCellValueFactory(new PropertyValueFactory<>("issn"));
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colPeriodicidad.setCellValueFactory(new PropertyValueFactory<>("periodicidad"));
        
        cargarDatosTabla();
        configurarEstadoInicialBotones();

        // Si es perfil Consulta, bloqueamos las cajas de texto para que solo puedan leer
        if (LoginController.usuarioSesionActual != null && LoginController.usuarioSesionActual.getNombreRol().equals("Consulta")) {
            txtTitulo.setDisable(true);
            txtEditorial.setDisable(true);
            txtIssn.setDisable(true);
            txtNumero.setDisable(true);
            txtPeriodicidad.setDisable(true);
        }

        txtTitulo.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtEditorial.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtIssn.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtNumero.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());
        txtPeriodicidad.textProperty().addListener((obs, oldVal, newVal) -> validarCamposEnTiempoReal());

        tablaRevistas.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtId.setText(String.valueOf(newSel.getId()));
                txtTitulo.setText(newSel.getTitulo());
                txtEditorial.setText(newSel.getEditorial());
                txtIssn.setText(newSel.getIssn());
                txtNumero.setText(String.valueOf(newSel.getNumero()));
                txtPeriodicidad.setText(newSel.getPeriodicidad());
                
                // VALIDACIÓN DE PERMISOS AL SELECCIONAR UNA FILA
                boolean esConsulta = false;
                boolean esBibliotecario = false;
                
                if (LoginController.usuarioSesionActual != null) {
                    String rol = LoginController.usuarioSesionActual.getNombreRol();
                    esConsulta = rol.equals("Consulta");
                    esBibliotecario = rol.equals("Bibliotecario");
                }
                
                btnGuardar.setDisable(true);
                btnEditar.setDisable(esConsulta); // Se bloquea si es Consulta
                btnEliminar.setDisable(esConsulta || esBibliotecario); // Se bloquea si es Consulta o Bibliotecario
            }
        });
    }

    private void cargarDatosTabla() {
        listaRevistas.clear();
        listaRevistas.addAll(revistaDAO.obtenerTodos());
        tablaRevistas.setItems(listaRevistas);
    }

    @FXML
    private void accionGuardar(ActionEvent event) {
        Revista nuevaRevista = new Revista();
        nuevaRevista.setTitulo(txtTitulo.getText());
        nuevaRevista.setEditorial(txtEditorial.getText());
        nuevaRevista.setIssn(txtIssn.getText());
        nuevaRevista.setNumero(Integer.parseInt(txtNumero.getText()));
        nuevaRevista.setPeriodicidad(txtPeriodicidad.getText());

        if (revistaDAO.insertar(nuevaRevista)) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Revista guardada correctamente.");
            cargarDatosTabla();
            accionLimpiar(null);
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo guardar la revista.");
        }
    }

    @FXML
    private void accionEditar(ActionEvent event) {
        Revista revistaSeleccionada = tablaRevistas.getSelectionModel().getSelectedItem();
        if (revistaSeleccionada != null) {
            revistaSeleccionada.setTitulo(txtTitulo.getText());
            revistaSeleccionada.setEditorial(txtEditorial.getText());
            revistaSeleccionada.setIssn(txtIssn.getText());
            revistaSeleccionada.setNumero(Integer.parseInt(txtNumero.getText()));
            revistaSeleccionada.setPeriodicidad(txtPeriodicidad.getText());

            if (revistaDAO.actualizar(revistaSeleccionada)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Revista actualizada correctamente.");
                cargarDatosTabla();
                accionLimpiar(null);
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo actualizar la revista.");
            }
        }
    }

    @FXML
    private void accionEliminar(ActionEvent event) {
        Revista revistaSeleccionada = tablaRevistas.getSelectionModel().getSelectedItem();
        if (revistaSeleccionada != null) {
            if (revistaDAO.eliminar(revistaSeleccionada.getId())) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Revista eliminada correctamente.");
                cargarDatosTabla();
                accionLimpiar(null);
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo eliminar la revista.");
            }
        }
    }

    private void validarCamposEnTiempoReal() {
        boolean valido = true;

        if (Validador.esVacio(txtTitulo.getText())) {
            lblErrorTitulo.setText("El título es obligatorio.");
            valido = false;
        } else {
            lblErrorTitulo.setText("");
        }

        if (Validador.esVacio(txtEditorial.getText())) {
            lblErrorEditorial.setText("La editorial es obligatoria.");
            valido = false;
        } else {
            lblErrorEditorial.setText("");
        }

        if (Validador.esVacio(txtIssn.getText())) {
            lblErrorIssn.setText("El ISSN es obligatorio.");
            valido = false;
        } else {
            lblErrorIssn.setText("");
        }

        if (Validador.esVacio(txtNumero.getText())) {
            lblErrorNumero.setText("El número es obligatorio.");
            valido = false;
        } else if (!Validador.esNumeroEntero(txtNumero.getText())) {
            lblErrorNumero.setText("Solo números enteros.");
            valido = false;
        } else {
            lblErrorNumero.setText("");
        }

        if (Validador.esVacio(txtPeriodicidad.getText())) {
            lblErrorPeriodicidad.setText("La periodicidad es obligatoria.");
            valido = false;
        } else {
            lblErrorPeriodicidad.setText("");
        }

        // VALIDACIÓN DE PERMISOS PARA EL BOTÓN GUARDAR
        boolean esConsulta = (LoginController.usuarioSesionActual != null && LoginController.usuarioSesionActual.getNombreRol().equals("Consulta"));
        if (esConsulta) {
            btnGuardar.setDisable(true);
        } else {
            btnGuardar.setDisable(!valido || !txtId.getText().isEmpty());
        }
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
        txtTitulo.clear();
        txtEditorial.clear();
        txtIssn.clear();
        txtNumero.clear();
        txtPeriodicidad.clear();

        lblErrorTitulo.setText("");
        lblErrorEditorial.setText("");
        lblErrorIssn.setText("");
        lblErrorNumero.setText("");
        lblErrorPeriodicidad.setText("");

        configurarEstadoInicialBotones();
        tablaRevistas.getSelectionModel().clearSelection();
    }

    private void configurarEstadoInicialBotones() {
        boolean esConsulta = (LoginController.usuarioSesionActual != null && LoginController.usuarioSesionActual.getNombreRol().equals("Consulta"));
        
        btnNuevo.setDisable(esConsulta);
        btnValidar.setDisable(esConsulta);
        btnLimpiar.setDisable(false); // El botón Limpiar siempre está activo para todos
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
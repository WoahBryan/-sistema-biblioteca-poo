module com.mycompany.biblioteca {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.utj.dsm.poo.biblioteca to javafx.fxml;
    opens edu.utj.dsm.poo.biblioteca.controlador to javafx.fxml;
    // Esta línea es la que le permite a la tabla leer los datos de tu clase Usuario:
    opens edu.utj.dsm.poo.biblioteca.modelo to javafx.base, javafx.fxml;

    exports edu.utj.dsm.poo.biblioteca;
    exports edu.utj.dsm.poo.biblioteca.controlador;
    exports edu.utj.dsm.poo.biblioteca.modelo;
}
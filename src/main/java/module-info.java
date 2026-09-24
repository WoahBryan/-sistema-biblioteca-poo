module com.mycompany.biblioteca {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens edu.utj.dsm.poo.biblioteca to javafx.fxml;
    exports edu.utj.dsm.poo.biblioteca;
}

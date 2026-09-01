module com.example.agenda {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    exports com.example.agenda.gestion;
    opens com.example.agenda.gestion to javafx.fxml;
    exports com.example.agenda.conexion;
    opens com.example.agenda.conexion to javafx.fxml;
    exports com.example.agenda.modulos;
    opens com.example.agenda.modulos to javafx.fxml;
}
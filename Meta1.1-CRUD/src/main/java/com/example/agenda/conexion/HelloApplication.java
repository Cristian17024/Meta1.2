package com.example.agenda.conexion;

import com.example.agenda.gestion.GestionPersonasBD;
import com.example.agenda.gestion.GestionTelefonosBD;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/agenda/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 700);

        HelloController controlador = fxmlLoader.getController();
        controlador.setDependencias(new GestionPersonasBD(), new GestionTelefonosBD());

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
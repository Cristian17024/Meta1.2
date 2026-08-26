package com.example.agenda;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class HelloController {
    @FXML
    private TextField campoNombre;

    @FXML
    private TextField campoDireccion;

    @FXML
    private TableView<Persona> tablaPersonas;

    @FXML
    private TableColumn<Persona, Integer> columnaId;

    @FXML
    private TableColumn<Persona, String> columnaNombre;

    @FXML
    private TableColumn<Persona, String> columnaDireccion;
    @FXML
    private TextField campoTelefono;

    @FXML
    private TableView<Telefono> tablaTelefonos;

    @FXML
    private TableColumn<Telefono, Integer> columnaTelefonoId;

    @FXML
    private TableColumn<Telefono, String> columnaNumeroTelefono;
    private GestionPersonasBD gestionPersonasBD;
    private GestionTelefonosBD gestionTelefonosBD;

    private ObservableList<Persona> personas;
    private ObservableList<Telefono> telefonos;
    @FXML
    public void initialize() {

        gestionPersonasBD = new GestionPersonasBD();
        gestionTelefonosBD = new GestionTelefonosBD();
        columnaId.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        columnaNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombre")
        );

        columnaDireccion.setCellValueFactory(
                new PropertyValueFactory<>("direccion")
        );
        columnaTelefonoId.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        columnaNumeroTelefono.setCellValueFactory(
                new PropertyValueFactory<>("telefono")
        );

        cargarPersonas();
        tablaPersonas.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, personaAnterior, personaSeleccionada) -> {

                    if (personaSeleccionada != null) {

                        campoNombre.setText(
                                personaSeleccionada.getNombre()
                        );

                        campoDireccion.setText(
                                personaSeleccionada.getDireccion()
                        );

                        cargarTelefonos(
                                personaSeleccionada.getId()
                        );

                    } else {

                        tablaTelefonos.getItems().clear();
                    }
                });
    }
    private void cargarPersonas() {

        personas = FXCollections.observableArrayList(
                gestionPersonasBD.obtenerTodasLasPersonas()
        );

        tablaPersonas.setItems(personas);
    }


    @FXML
    private void agregarPersona() {

        String nombre = campoNombre.getText();
        String direccion = campoDireccion.getText();

        if (nombre.isBlank()) {

            mostrarMensaje(
                    "Error",
                    "El nombre es obligatorio."
            );

            return;
        }

        Persona persona = new Persona(
                nombre,
                direccion
        );

        if (gestionPersonasBD.agregarPersona(persona)) {

            cargarPersonas();
            limpiarCampos();

            mostrarMensaje(
                    "Hecho",
                    "La persona fue agregada."
            );
        }
    }

    @FXML
    private void modificarPersona() {

        Persona personaSeleccionada =
                tablaPersonas.getSelectionModel()
                        .getSelectedItem();

        if (personaSeleccionada == null) {

            mostrarMensaje(
                    "Aviso",
                    "Selecciona una persona para modificar."
            );

            return;
        }

        String nombre = campoNombre.getText();

        if (nombre.isBlank()) {

            mostrarMensaje(
                    "Error",
                    "El nombre es obligatorio."
            );

            return;
        }

        personaSeleccionada.setNombre(nombre);

        personaSeleccionada.setDireccion(
                campoDireccion.getText()
        );

        if (gestionPersonasBD.modificarPersona(
                personaSeleccionada)) {

            cargarPersonas();
            limpiarCampos();

            mostrarMensaje(
                    "Éxito",
                    "La persona fue modificada correctamente."
            );
        }
    }
    @FXML
    private void eliminarPersona() {

        Persona personaSeleccionada =
                tablaPersonas.getSelectionModel()
                        .getSelectedItem();

        if (personaSeleccionada == null) {

            mostrarMensaje(
                    "Aviso",
                    "Selecciona una persona para eliminar."
            );

            return;
        }

        if (gestionPersonasBD.eliminarPersona(
                personaSeleccionada.getId())) {

            cargarPersonas();
            limpiarCampos();

            mostrarMensaje(
                    "Éxito",
                    "La persona fue eliminada correctamente."
            );
        }
    }
    @FXML
    private void limpiarCampos() {

        campoNombre.clear();
        campoDireccion.clear();
        campoTelefono.clear();

        tablaPersonas.getSelectionModel()
                .clearSelection();

        tablaTelefonos.getItems().clear();
    }
    private void cargarTelefonos(int personaId) {

        telefonos = FXCollections.observableArrayList(
                gestionTelefonosBD.obtenerTelefonosDePersona(
                        personaId
                )
        );

        tablaTelefonos.setItems(telefonos);
    }
    @FXML
    private void agregarTelefono() {

        Persona personaSeleccionada =
                tablaPersonas.getSelectionModel()
                        .getSelectedItem();

        if (personaSeleccionada == null) {

            mostrarMensaje(
                    "Aviso",
                    "Primero selecciona una persona."
            );

            return;
        }

        String numeroTelefono =
                campoTelefono.getText();

        if (numeroTelefono.isBlank()) {

            mostrarMensaje(
                    "Error",
                    "Escribe un número de teléfono."
            );

            return;
        }

        Telefono telefono = new Telefono(
                personaSeleccionada.getId(),
                numeroTelefono
        );

        if (gestionTelefonosBD.agregarTelefono(
                telefono)) {

            cargarTelefonos(
                    personaSeleccionada.getId()
            );

            campoTelefono.clear();

            mostrarMensaje(
                    "Éxito",
                    "El teléfono fue agregado correctamente."
            );
        }
    }
    @FXML
    private void modificarTelefono() {

        Telefono telefonoSeleccionado =
                tablaTelefonos.getSelectionModel()
                        .getSelectedItem();

        if (telefonoSeleccionado == null) {

            mostrarMensaje(
                    "Aviso",
                    "Selecciona un teléfono para modificar."
            );

            return;
        }

        String numeroTelefono =
                campoTelefono.getText();

        if (numeroTelefono.isBlank()) {

            mostrarMensaje(
                    "Error",
                    "Escribe un número de teléfono."
            );

            return;
        }

        telefonoSeleccionado.setTelefono(
                numeroTelefono
        );

        if (gestionTelefonosBD.modificarTelefono(
                telefonoSeleccionado)) {

            Persona personaSeleccionada =
                    tablaPersonas.getSelectionModel()
                            .getSelectedItem();

            cargarTelefonos(
                    personaSeleccionada.getId()
            );

            campoTelefono.clear();

            mostrarMensaje(
                    "Éxito",
                    "El teléfono fue modificado correctamente."
            );
        }
    }
    @FXML
    private void eliminarTelefono() {

        Telefono telefonoSeleccionado =
                tablaTelefonos.getSelectionModel()
                        .getSelectedItem();

        if (telefonoSeleccionado == null) {

            mostrarMensaje(
                    "Aviso",
                    "Selecciona un teléfono para eliminar."
            );

            return;
        }

        if (gestionTelefonosBD.eliminarTelefono(
                telefonoSeleccionado.getId())) {

            Persona personaSeleccionada =
                    tablaPersonas.getSelectionModel()
                            .getSelectedItem();

            cargarTelefonos(
                    personaSeleccionada.getId()
            );

            campoTelefono.clear();

            mostrarMensaje(
                    "Éxito",
                    "El teléfono fue eliminado correctamente."
            );
        }
    }
    private void mostrarMensaje(
            String titulo,
            String mensaje) {

        Alert alerta =
                new Alert(Alert.AlertType.INFORMATION);

        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        alerta.showAndWait();
    }
}
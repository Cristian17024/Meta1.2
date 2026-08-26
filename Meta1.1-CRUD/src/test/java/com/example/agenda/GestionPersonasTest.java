package com.example.agenda;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestionPersonasBDTest {

    @Test
    void probarAgregarPersona() {

        GestionPersonasBD gestion = new GestionPersonasBD();

        Persona persona = new Persona(
                0,
                "PersonaPrueba",
                "DireccionPrueba"
        );

        boolean resultado = gestion.agregarPersona(persona);

        assertTrue(resultado);

        List<Persona> personas = gestion.obtenerTodasLasPersonas();

        boolean encontrada = personas.stream()
                .anyMatch(p ->
                        p.getNombre().equals("PersonaPrueba") &&
                                p.getDireccion().equals("DireccionPrueba")
                );

        assertTrue(encontrada);
    }

    @Test
    void probarObtenerTodasLasPersonas() {

        GestionPersonasBD gestion = new GestionPersonasBD();

        List<Persona> personas = gestion.obtenerTodasLasPersonas();

        assertNotNull(personas);
    }

    @Test
    void probarModificarPersona() {

        GestionPersonasBD gestion = new GestionPersonasBD();

        Persona persona = new Persona(
                0,
                "PersonaModificar",
                "DireccionOriginal"
        );

        assertTrue(gestion.agregarPersona(persona));

        List<Persona> personas = gestion.obtenerTodasLasPersonas();

        Persona personaAgregada = personas.stream()
                .filter(p -> p.getNombre().equals("PersonaModificar"))
                .findFirst()
                .orElse(null);

        assertNotNull(personaAgregada);

        personaAgregada.setNombre("PersonaModificada");
        personaAgregada.setDireccion("DireccionModificada");

        assertTrue(gestion.modificarPersona(personaAgregada));

        personas = gestion.obtenerTodasLasPersonas();

        Persona personaModificada = personas.stream()
                .filter(p -> p.getId() == personaAgregada.getId())
                .findFirst()
                .orElse(null);

        assertNotNull(personaModificada);
        assertEquals("PersonaModificada", personaModificada.getNombre());
        assertEquals("DireccionModificada", personaModificada.getDireccion());
    }

    @Test
    void probarEliminarPersona() {

        GestionPersonasBD gestion = new GestionPersonasBD();

        Persona persona = new Persona(
                0,
                "PersonaEliminar",
                "DireccionEliminar"
        );

        assertTrue(gestion.agregarPersona(persona));

        List<Persona> personas = gestion.obtenerTodasLasPersonas();

        Persona personaAgregada = personas.stream()
                .filter(p -> p.getNombre().equals("PersonaEliminar"))
                .findFirst()
                .orElse(null);

        assertNotNull(personaAgregada);

        int id = personaAgregada.getId();

        assertTrue(gestion.eliminarPersona(id));

        personas = gestion.obtenerTodasLasPersonas();

        boolean existe = personas.stream()
                .anyMatch(p -> p.getId() == id);

        assertFalse(existe);
    }
}
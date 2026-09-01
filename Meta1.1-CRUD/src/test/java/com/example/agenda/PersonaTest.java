package com.example.agenda;

import com.example.agenda.modulos.Persona;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonaTest {

    @Test
    void crearPersonaCorrectamente() {

        Persona persona = new Persona(
                "Juan Pérez",
                "Calle 10"
        );

        assertEquals("Juan Pérez", persona.getNombre());
        assertEquals("Calle 10", persona.getDireccion());
    }


    @Test
    void modificarNombreCorrectamente() {

        Persona persona = new Persona(
                "Juan Pérez",
                "Calle 10"
        );

        persona.setNombre("Pedro López");

        assertEquals(
                "Pedro López",
                persona.getNombre()
        );
    }


    @Test
    void modificarDireccionCorrectamente() {

        Persona persona = new Persona(
                "Juan Pérez",
                "Calle 10"
        );

        persona.setDireccion("Calle 20");

        assertEquals(
                "Calle 20",
                persona.getDireccion()
        );
    }


    @Test
    void crearPersonaConId() {

        Persona persona = new Persona(
                5,
                "Ana López",
                "Calle 30"
        );

        assertEquals(5, persona.getId());
        assertEquals("Ana López", persona.getNombre());
        assertEquals("Calle 30", persona.getDireccion());
    }
}
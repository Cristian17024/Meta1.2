package com.example.agenda;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TelefonoTest {

    @Test
    void crearTelefonoCorrectamente() {

        Telefono telefono = new Telefono(
                1,
                1,
                "555-1234567"
        );

        assertEquals(1, telefono.getId());
        assertEquals(1, telefono.getPersonaId());
        assertEquals(
                "555-1234567",
                telefono.getTelefono()
        );
    }


    @Test
    void modificarTelefonoCorrectamente() {

        Telefono telefono = new Telefono(
                1,
                1,
                "555-1234567"
        );

        telefono.setTelefono("555-9876543");

        assertEquals(
                "555-9876543",
                telefono.getTelefono()
        );
    }


    @Test
    void modificarPersonaDelTelefono() {

        Telefono telefono = new Telefono(
                1,
                1,
                "555-1234567"
        );

        telefono.setPersonaId(2);

        assertEquals(
                2,
                telefono.getPersonaId()
        );
    }


    @Test
    void crearTelefonoSinId() {

        Telefono telefono = new Telefono(
                3,
                "555-7654321"
        );

        assertEquals(
                3,
                telefono.getPersonaId()
        );

        assertEquals(
                "555-7654321",
                telefono.getTelefono()
        );
    }
}
package com.example.agenda.gestion;

import com.example.agenda.modulos.Persona;
import java.util.List;

public interface IPersonaDAO {
    List<Persona> obtenerTodasLasPersonas();
    boolean agregarPersona(Persona persona);
    boolean modificarPersona(Persona persona);
    boolean eliminarPersona(int id);
}

package com.example.agenda.gestion;

import com.example.agenda.modulos.Telefono;
import java.util.List;

public interface ITelefonoDAO {
    List<Telefono> obtenerTelefonosDePersona(int personaId);
    boolean agregarTelefono(Telefono telefono);
    boolean modificarTelefono(Telefono telefono);
    boolean eliminarTelefono(int id);
}
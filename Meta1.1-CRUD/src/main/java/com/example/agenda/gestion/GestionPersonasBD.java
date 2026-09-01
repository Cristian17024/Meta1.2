package com.example.agenda.gestion;

import com.example.agenda.conexion.ConexionBD;
import com.example.agenda.modulos.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GestionPersonasBD implements IPersonaDAO {

    public List<Persona> obtenerTodasLasPersonas() {

        List<Persona> personas = new ArrayList<>();

        String sql = "SELECT id, nombre, direccion FROM Personas";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                Persona persona = new Persona(
                        resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getString("direccion")
                );

                personas.add(persona);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return personas;
    }

    public boolean agregarPersona(Persona persona) {

        String sql = "INSERT INTO Personas (nombre, direccion) VALUES (?, ?)";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, persona.getNombre());
            sentencia.setString(2, persona.getDireccion());

            sentencia.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean modificarPersona(Persona persona) {

        String sql = "UPDATE Personas SET nombre = ?, direccion = ? WHERE id = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, persona.getNombre());
            sentencia.setString(2, persona.getDireccion());
            sentencia.setInt(3, persona.getId());

            sentencia.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarPersona(int id) {

        String sql = "DELETE FROM Personas WHERE id = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            sentencia.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}